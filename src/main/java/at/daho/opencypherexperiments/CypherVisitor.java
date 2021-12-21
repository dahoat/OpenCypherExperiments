package at.daho.opencypherexperiments;

import at.daho.opencypherexperiments.antlr4.CypherBaseVisitor;
import at.daho.opencypherexperiments.antlr4.CypherParser;

import java.util.ArrayList;

public class CypherVisitor extends CypherBaseVisitor<Object> {
	private int clauseNumber = 1;
	private int indent = 0;
	private ArrayList<String> currentGroup = new ArrayList<>();

	@Override
	public Object visitOC_MultiPartQuery(CypherParser.OC_MultiPartQueryContext ctx) {
		println("MultiPart [");
		indent++;
		currentGroup.clear();
		Object result = super.visitOC_MultiPartQuery(ctx);
		indent--;
		println("]");

		return result;
	}

	@Override
	public Object visitOC_ReadingClause(CypherParser.OC_ReadingClauseContext ctx) {
		currentGroup.add("" + (clauseNumber++) + ": " + ctx.getText());
		return super.visitOC_ReadingClause(ctx);
	}

	@Override
	public Object visitOC_UpdatingClause(CypherParser.OC_UpdatingClauseContext ctx) {
		currentGroup.add("" + (clauseNumber++) + ": " + ctx.getText());
		return super.visitOC_UpdatingClause(ctx);
	}

	@Override
	public Object visitOC_With(CypherParser.OC_WithContext ctx) {
		println("Group [");
		indent++;
		currentGroup.add("" + (clauseNumber++) + ": " + ctx.getText());
		Object result = super.visitOC_With(ctx);
		currentGroup.stream().forEachOrdered(this::println);
		indent--;
		println("]");
		currentGroup.clear();
		return result;
	}

	@Override
	public Object visitOC_Return(CypherParser.OC_ReturnContext ctx) {
		println("Group [");
		indent++;
		currentGroup.add("" + (clauseNumber++) + ": " + ctx.getText());
		Object result = super.visitOC_Return(ctx);
		currentGroup.stream().forEachOrdered(this::println);
		indent--;
		println("]");
		return result;
	}

	private void println(String line) {
		System.out.println(getIndent() + line);
	}

	private String getIndent() {
		return new String(new char[indent]).replace("\0", "  ");
	}
}
