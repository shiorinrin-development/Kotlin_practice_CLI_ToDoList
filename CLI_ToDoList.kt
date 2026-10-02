class Task (val ID: Int, val taskName: String, var isFinished: Boolean = false) {
	fun displayInfo() {
		if (isFinished) println("[ x ] $ID: $taskName") else println("[   ] $ID: $taskName")
	}

	fun check() {
		isFinished = true
		println("タスクを完了しました。")
	}
}

class TaskList (val name: String, val tasks: MutableList<Task> = mutableListOf(), var lastID: Int = 0) {
	fun addTask(taskName: String) {
		tasks.add(Task(lastID +1, taskName))
		println("タスクを追加しました。")
		lastID += 1
	}


	fun displayTasks() {		
		if (tasks.isEmpty()) {
			println("登録されているタスクは0件です。タスクを登録してください。")
			return
		}
		for (task in tasks) { task.displayInfo() }
		println("登録されているタスクは以上の${tasks.size}件です。")
		return
	}

	fun finishTask(ID: Int) {
		for (task in tasks) {
			if (task.ID == ID) { 
				task.check()
				return 
			}
		}
		println("ID: $ID であるタスクは存在しません。")
	}

	fun deleteTask(ID: Int) {
		for (task in tasks) {
			if (task.ID == ID) {
				val index = tasks.indexOf(task)
				tasks.removeAt(index)
				println("タスクを削除しました。")
				return
			}
		}
		println("ID: $ID であるタスクは存在しません。")
	}
}

fun displayOptions() {
    println("== Task Manager ==")
	val options: List<String> = listOf("1. タスクを追加", "2. タスク一覧", "3. タスクを完了", "4. タスクを削除", "5. 終了")
	for (option in options) {
		println(option)
	}
	println("==================")
	println("行いたい操作を番号で指定してください。")
}

fun main() {
    val taskList: TaskList = TaskList("taskList")
	while (true) {
        displayOptions()
        val input: String = readln()
	    when (input) {
		    "1" -> {
                println("追加したいタスクの内容を入力してください。")
				val taskName: String = readln()
				taskList.addTask(taskName)	
            }
		    "2" -> {
				taskList.displayTasks()
			}
		    "3" -> {
				println("完了したいタスクのIDを入力してください。")
				val id: Int? = readln().toIntOrNull()
				if (id == null) {
					println("IDは整数値で入力してください。")
				} else { taskList.finishTask(id) }
			}
		    "4" -> {
				println("削除したいタスクのIDを入力してください。")
				val id: Int? = readln().toIntOrNull()
				if (id == null) {
					println("IDは整数値で入力してください。")
				} else { taskList.deleteTask(id) }
			}
		    "5" -> return
		    else -> println("数値で選択してください。")
	    }
    }
}