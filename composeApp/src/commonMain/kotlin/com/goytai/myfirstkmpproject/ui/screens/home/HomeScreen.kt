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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.goytai.myfirstkmpproject.infra.di.ScreenModelParams
import com.goytai.myfirstkmpproject.infra.di.di
import com.goytai.myfirstkmpproject.ui.components.Input
import com.goytai.myfirstkmpproject.ui.components.ScreenContainer
import com.goytai.myfirstkmpproject.ui.components.ScreenHeader
import com.goytai.myfirstkmpproject.ui.screens.home.components.DayCard
import com.goytai.myfirstkmpproject.ui.screens.home.components.TaskItem
import org.kodein.di.compose.localDI

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel<ScreenModelParams, HomeScreenModel>(
            arg = ScreenModelParams(localDI(), LocalNavigator.currentOrThrow),
        )

        val newTaskInput by screenModel.newTaskInput.collectAsState()
        val tasks by screenModel.tasks.collectAsState()
        val selectedDate by screenModel.selectedDate.collectAsState()

        ScreenContainer {
            Column(modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())) {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    ScreenHeader(
                        title = "TO-DO"
                    ) {
                        IconButton(onClick = { screenModel.handleOnNavigateToSettings() }) {
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
                    modifier = Modifier.padding(bottom = 32.dp).horizontalScroll(rememberScrollState()),
                ) {
                    screenModel.dates.forEach {
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
                        TaskItem(task = it, onClick = { screenModel.handleOnToggleTaskState(it) })
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
                    onValueChange = { screenModel.handleOnTaskInputChange(it) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = { screenModel.handleOnAddNewTask() }),
                    placeholder = {
                        Text(text = "Escreva uma tarefa...", fontSize = 16.sp)
                    },
                )
                Button(
                    onClick = { screenModel.handleOnAddNewTask() },
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