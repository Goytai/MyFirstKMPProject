package com.goytai.myfirstkmpproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goytai.myfirstkmpproject.components.Input
import com.goytai.myfirstkmpproject.components.TaskItem
import com.goytai.myfirstkmpproject.data.model.Task
import com.goytai.myfirstkmpproject.domain.repository.ITaskRepository
import kotlinx.coroutines.launch
import org.kodein.di.compose.localDI
import org.kodein.di.instance

@Composable
fun HomeScreen() {

    val di = localDI()
    val taskRepository: ITaskRepository by di.instance()
    val coroutineScope = rememberCoroutineScope()

    val tasks = remember {
        mutableStateListOf<Task>()
    }
    var newTaskInput by remember { mutableStateOf("") }

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

    LaunchedEffect(Unit) {
        tasks.addAll(taskRepository.getAllTasks())
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets(0.dp, 0.dp, 0.dp, 0.dp))
            .safeDrawingPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 22.dp, vertical = 26.dp)
        ) {
            Column(modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())) {
                Text(
                    text = "TODO",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 34.dp, start = 16.dp, bottom = 32.dp)
                )

                if (tasks.isNotEmpty()) {
                    Text(
                        text = "TAREFAS",
                        fontSize = 12.sp,
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
                    modifier = Modifier.weight(weight = 3.5f),
                    onValueChange = { newTaskInput = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = { handleOnAddNewTask() }),
                    placeholder = {
                        Text(text = "Escreva uma tarefa...", fontSize = 16.sp)
                    },
                )
                Button(
                    onClick = { handleOnAddNewTask() },
                    modifier = Modifier.weight(1f).height(56.dp),
                    enabled = newTaskInput.trim().isNotEmpty(),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Text(text = "Criar", fontSize = 16.sp)
                }
            }
        }
    }
}