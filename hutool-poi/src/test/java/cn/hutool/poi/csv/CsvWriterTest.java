package cn.hutool.poi.csv;

import cn.hutool.core.io.file.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import java.io.File;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CsvWriterTest {

	@Test
	@Ignore
	public void writeWithAliasTest(){
		final CsvWriteConfig csvWriteConfig = CsvWriteConfig.defaultConfig()
				.addHeaderAlias("name", "姓名")
				.addHeaderAlias("gender", "性别");

		final CsvWriter writer = CsvUtil.getWriter(
				FileUtil.file("d:/test/csvAliasTest.csv"),
				CharsetUtil.GBK, false, csvWriteConfig);

		writer.writeHeaderLine("name", "gender", "address");
		writer.writeLine("张三", "男", "XX市XX区");
		writer.writeLine("李四", "男", "XX市XX区,01号");
		writer.close();
	}

	@Test
	@Ignore
	public void issue2255Test(){
		final String fileName = "D:/test/" + new Random().nextInt(100) + "-a.csv";
		final CsvWriter writer = CsvUtil.getWriter(fileName, CharsetUtil.UTF_8);
		final List<String> list = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			list.add(i+"");
		}
		Console.log("{} : {}", fileName, list.size());
		for (final String s : list) {
			writer.writeLine(s);
		}
		writer.close();
	}

	@Test
	public void issue3014Test(){
		File tmp = new File("/Users/test/Desktop/test.csv");
		CsvWriter writer = CsvUtil.getWriter(tmp, CharsetUtil.UTF_8);
		//设置 dde 安全模式
		writer.setDdeSafe(true);
		writer.write(
			new String[] {"=12+23"},
			new String[] {"-3+2+cmd |' /C calc' !A0"},
			new String[] {"@SUM(cmd|'/c calc'!A0)"}
		);
		writer.close();

		List<String> lines = FileUtil.readLines(tmp, CharsetUtil.UTF_8);
		Assert.assertEquals("\"=12+23\"",lines.get(0));
		Assert.assertEquals("\"-3+2+cmd |' /C calc' !A0\"",lines.get(1));
		Assert.assertEquals("\"@SUM(cmd|'/c calc'!A0)\"",lines.get(2));

	}



}
