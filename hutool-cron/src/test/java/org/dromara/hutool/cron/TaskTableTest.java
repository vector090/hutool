package org.dromara.hutool.cron;

import org.dromara.hutool.core.lang.Console;
import org.dromara.hutool.core.lang.id.IdUtil;
import org.dromara.hutool.cron.pattern.CronPattern;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TaskTableTest {

	@Test
	@Disabled
	public void toStringTest(){
		final TaskTable taskTable = new TaskTable();
		taskTable.add(IdUtil.fastUUID(), new CronPattern("*/10 * * * * *"), ()-> Console.log("Task 1"));
		taskTable.add(IdUtil.fastUUID(), new CronPattern("*/20 * * * * *"), ()-> Console.log("Task 2"));
		taskTable.add(IdUtil.fastUUID(), new CronPattern("*/30 * * * * *"), ()-> Console.log("Task 3"));

		Console.log(taskTable);
	}
}