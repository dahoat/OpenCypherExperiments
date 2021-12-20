package at.daho.opencypherexperiments;

import at.daho.opencypherexperiments.antlr4.CypherBaseVisitor;
import at.daho.opencypherexperiments.antlr4.CypherParser;

public class CypherVisitor extends CypherBaseVisitor<Object> {

	@Override
	public Object visitOC_Cypher(CypherParser.OC_CypherContext ctx) {
		return visit(ctx.oC_Statement());
	}

	@Override
	public Object visitOC_Statement(CypherParser.OC_StatementContext ctx) {
		return visit(ctx.oC_Query());
	}

	@Override
	public Object visitOC_Query(CypherParser.OC_QueryContext ctx) {
		return visit(ctx.oC_RegularQuery());
	}

	@Override
	public Object visitOC_RegularQuery(CypherParser.OC_RegularQueryContext ctx) {
		return visit(ctx.oC_SingleQuery());
	}

	@Override
	public Object visitOC_SingleQuery(CypherParser.OC_SingleQueryContext ctx) {
		return visit(ctx.oC_MultiPartQuery());
	}

	@Override
	public Object visitOC_MultiPartQuery(CypherParser.OC_MultiPartQueryContext ctx) {
		System.out.println("Enter visitOC_MultiPartQuery");
		System.out.println("Whole context: " + ctx.getText());
		System.out.println("OC_ReadingClause:");
		ctx.oC_ReadingClause().forEach(c -> System.out.println("\t" + c.getText()));
		System.out.println("OC_UpdatingClause:");
		ctx.oC_UpdatingClause().forEach(c -> System.out.println("\t" + c.getText()));
		System.out.println("To which OC_ReadingClause does this OC_UpdatingClause belong?");
		System.out.println("Leave visitOC_MultiPartQuery");

		return null;
	}
}
