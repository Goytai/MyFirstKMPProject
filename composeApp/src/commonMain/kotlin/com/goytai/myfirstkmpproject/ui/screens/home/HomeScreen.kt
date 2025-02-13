package com.goytai.myfirstkmpproject.ui.screens.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.goytai.myfirstkmpproject.data.model.Task
import com.goytai.myfirstkmpproject.domain.repository.ITaskRepository
import com.goytai.myfirstkmpproject.ui.components.*
import com.goytai.myfirstkmpproject.ui.screens.home.components.DayCard
import com.goytai.myfirstkmpproject.ui.screens.home.components.TaskItem
import com.goytai.myfirstkmpproject.ui.screens.settings.SettingsScreen
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import org.kodein.di.compose.localDI
import org.kodein.di.instance

class HomeScreen: Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val di = localDI()
        val taskRepository: ITaskRepository by di.instance()
        val coroutineScope = rememberCoroutineScope()

        var newTaskInput by remember { mutableStateOf("") }
        val tasks = remember { mutableStateListOf<Task>() }


        val selectedDate by remember {
            val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            val initialDate = with (now) {
                LocalDate(
                    dayOfMonth = dayOfMonth,
                    month = month,
                    year = year
                )
            }
            mutableStateOf(initialDate)
        }
        val dates = remember {
            val now = Clock.System.now()
            val localTimezone = TimeZone.currentSystemDefault()
            val initialDates = (-1..14).map {
                now.plus(it, DateTimeUnit.DAY, localTimezone).toLocalDateTime(localTimezone).run {
                    LocalDate(
                        dayOfMonth = dayOfMonth,
                        month = month,
                        year = year
                    )
                }
            }

            initialDates.toMutableStateList()
        }

        fun handleOnAddNewTask() {
            if (newTaskInput.trim().isEmpty()) return

            val task = Task(name=newTaskInput.trim(), isDone=false)

            tasks.add(task)

            coroutineScope.launch {
                taskRepository.insertTask(task)
            }

            newTaskInput = ""
        }

        fun handleOnToggleTaskState(task: Task) {
            val taskIndex = tasks.indexOf(task)
            val newTask = task.copy(isDone=!task.isDone)
            tasks[taskIndex] = newTask

            coroutineScope.launch {
                taskRepository.updateTask(newTask)
            }
        }

        fun handleOnNavigateToSetting() {
            navigator.push(SettingsScreen())
        }

        LaunchedEffect(Unit) {
            tasks.addAll(taskRepository.getAllTasks())
        }

        ScreenContainer {
            Column(modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())) {
                Box (modifier = Modifier.padding(horizontal = 16.dp)) {
                    ScreenHeader(
                        title = "TO-DO"
                    ) {
                        IconButton(onClick = { handleOnNavigateToSetting() }) {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                contentDescription = "Settings",
                                tint = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding( bottom = 32.dp).horizontalScroll(rememberScrollState()),
                ) {
                    dates.forEach {
                        DayCard(
                            date = it,
                            isSelected = selectedDate == it
                        )
                    }
                }

                if (tasks.isNotEmpty()) {
                    Text(
                        text = "TAREFAS",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )

                    tasks.forEach {
                        TaskItem(task = it, onClick = { handleOnToggleTaskState(it) })
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Input(
                    value = newTaskInput,
                    modifier = Modifier.weight(weight = 3f),
                    onValueChange = { newTaskInput = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = { handleOnAddNewTask() }),
                    placeholder = {
                        Text(text = "Escreva uma tarefa...", fontSize = 16.sp)
                    },
                )
                Button(
                    onClick = { handleOnAddNewTask() },
                    modifier = Modifier.weight(1.5f).height(56.dp),
                    enabled = newTaskInput.trim().isNotEmpty(),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Text(text = "Criar", fontSize = 16.sp)
                }
            }
        }
    }
}