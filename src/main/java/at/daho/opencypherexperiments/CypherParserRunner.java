package at.daho.opencypherexperiments;

import at.daho.opencypherexperiments.antlr4.CypherLexer;
import at.daho.opencypherexperiments.antlr4.CypherParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CypherParserRunner implements CommandLineRunner {

	String cypher = "MATCH (a) WITH a MATCH (b) SET a.prop = 'match 2' WITH a, b MATCH (c) WITH a, b, c MATCH (d) RETURN *";

	@Override
	public void run(String... args) throws Exception {
		CypherLexer lexer = new CypherLexer(CharStreams.fromString(cypher));
		CypherParser parser = new CypherParser(new CommonTokenStream(lexer));
		ParseTree tree = parser.oC_Cypher();
		new CypherVisitor().visit(tree);
	}
}
