package test;

import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class HtmlOutput {
	/**
	 * @param args
	 */
	public static void htmlOutput() throws Exception {
		Velocity.init();

		if (true) {
			String b = null;
			b = "abe";
			b = b.toString();
		}
		VelocityContext context = new VelocityContext();
		context.put("PageTitle", "Accounting List Report");
		List<List<String>> conditions = new ArrayList<List<String>>();
		List<String> condition = new ArrayList<String>();
		condition.add("CollectPeriodLabel");
		condition.add("2010/09/30 - 2010/09/30");
		conditions.add(condition);
		condition = new ArrayList<String>();
		condition.add("device");
		condition.add("abc");
		condition.add("efg");
		condition.add("abc");
		condition.add("efg");
		condition.add("abc");
		condition.add("efg");
		conditions.add(condition);
		condition = new ArrayList<String>();
		condition.add("dept");
		condition.add("abc");
		condition.add("efg");
		condition.add("abc");
		condition.add("efg");
		condition.add("abc");
		condition.add("efg");
		conditions.add(condition);
		context.put("Condition", conditions);
		List<String> tblTitle = new ArrayList<String>();
		List<String> cellAlign = new ArrayList<String>();
		tblTitle.add("Organization Name");
		cellAlign.add("textLeft");
		for (int j = 0; j < 20;j++) {
		tblTitle.add("Impressions ");
		cellAlign.add("textRight");
		
		}
		tblTitle.add("Sheets");
		cellAlign.add("textRight");
		tblTitle.add("Jobs");
		cellAlign.add("textRight");
		context.put("TableTitle", tblTitle);
		context.put("CellAlign", cellAlign);
		
		List<List<String>> tblValue = new ArrayList<List<String>>();
		List<String> row = new ArrayList<String>();
		for (int i = 0; i < 30; i++ ){
		row = new ArrayList<String>();
		row.add("Unknown");
		for (int j = 0; j < 20;j++)
		row.add("216");
		row.add("216");
		row.add("216 ");
		tblValue.add(row);
		}
		context.put("TableValue", tblValue);
		Template template = Velocity.getTemplate("src/main/resources/htmlTemplate.vm");
		StringWriter sw = new StringWriter();
		FileWriter fw = new FileWriter("target/output.html");
		template.merge(context, sw);
		System.out.println(sw.toString());
		template.merge(context, fw);
		fw.flush();
		fw.close();
	}
	
	public static void main(String[] args) throws Exception {
		HtmlOutput.htmlOutput();
	}
}
