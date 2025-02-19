package com.goytai.myfirstkmpproject.ui.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.goytai.myfirstkmpproject.data.model.Task
import com.goytai.myfirstkmpproject.domain.repository.ITaskRepository
import com.goytai.myfirstkmpproject.infra.di.ScreenModelParams
import com.goytai.myfirstkmpproject.ui.screens.settings.SettingsScreen
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.*

class HomeScreenModel(
  private val params: ScreenModelParams,
  private val taskRepository: ITaskRepository
) : ScreenModel {
  private val navigator = params.navigator

  private val _newTaskInput = MutableStateFlow("")
  val newTaskInput: StateFlow<String> get() = _newTaskInput.asStateFlow()

  private val _tasks = MutableStateFlow(mutableListOf<Task>())
  val tasks: StateFlow<MutableList<Task>> get() = _tasks.asStateFlow()

  private val _selectedDate = MutableStateFlow(getTodayLocalDate())
  val selectedDate: StateFlow<LocalDate> get() = _selectedDate.asStateFlow()

  val dates = getLocalNextDaysWithToday(14)


  // Init
  init {
    fetchTasksByScheduleDate(scheduleDate = selectedDate.value)
    onChangeSelectedDate()
  }

  // Observers
  private fun onChangeSelectedDate() {
    screenModelScope.launch {
      _selectedDate.collect {
        fetchTasksByScheduleDate(it)
      }
    }
  }


  // Private Methods
  private fun getTodayLocalDate(): LocalDate {
    val now = Clock.System.now()
      .toLocalDateTime(TimeZone.currentSystemDefault())

    return LocalDate(
      dayOfMonth = now.dayOfMonth,
      month = now.month,
      year = now.year
    )
  }

  private fun getLocalNextDaysWithToday(end: Int, start: Int = -1): List<LocalDate> {
    val now = Clock.System.now()
    val tz = TimeZone.currentSystemDefault()

    return (start..end).map {
      now.plus(it, DateTimeUnit.DAY, tz)
        .toLocalDateTime(tz)
        .run {
          LocalDate(
            dayOfMonth = dayOfMonth,
            month = month,
            year = year
          )
        }
    }
  }

  private fun fetchTasksByScheduleDate(scheduleDate: LocalDate) {
    screenModelScope.launch {
      val savedTasks = taskRepository.getTasksByScheduleDate(scheduleDate)

      _tasks.update { savedTasks.toMutableList() }
    }
  }


  // Public Methods
  fun handleOnTaskInputChange(newValue: String) {
    _newTaskInput.update { newValue }
  }

  fun handleOnAddNewTask() {
    if (_newTaskInput.value.trim().isEmpty()) return

    val task = Task(
      name = _newTaskInput.value.trim(),
      isDone = false,
      scheduleDate = selectedDate.value
    )

    _tasks.update {
      it.add(task)
      it
    }

    screenModelScope.launch {
      taskRepository.insertTask(task)
    }

    _newTaskInput.update { "" }
  }

  fun handleOnToggleTaskState(task: Task) {
    val taskIndex = _tasks.value.indexOf(task)
    val newTask = task.copy(isDone = !task.isDone)

    _tasks.update {
      it.toMutableList().apply {
        this[taskIndex] = newTask
      }
    }

    screenModelScope.launch {
      taskRepository.updateTask(newTask)
    }
  }

  fun handleOnNavigateToSettings() {
    navigator.push(SettingsScreen())
  }

  fun handleOnChangeSelectedDate(date: LocalDate) {
    _selectedDate.update { date }
  }

}