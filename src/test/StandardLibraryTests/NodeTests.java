package test.StandardLibraryTests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import test.TestFramework;
import static org.junit.Assert.*;

public class NodeTests extends TestFramework {

	@Test
	public void arity() {

		prepare("import Node;").prepareMore(
				"data XNODE = xf | xf(int) | xf(int,int) | xf(int,int,int);");

		assertTrue(runTestInSameEvaluator("arity(xf()) == 0;"));
		assertTrue(runTestInSameEvaluator("arity(xf(1)) == 1;"));
		assertTrue(runTestInSameEvaluator("arity(xf(1,2)) == 2;"));
	}

	@Test
	public void getChildren() {

		prepare("import Node;").prepareMore(
				"data YNODE = yf | yf(int) | yf(int,int) | yf(int,int,int);");

		assertTrue(runTestInSameEvaluator("getChildren(yf()) == [];"));
		assertTrue(runTestInSameEvaluator("getChildren(yf(1)) == [1];"));
		assertTrue(runTestInSameEvaluator("getChildren(yf(1,2)) == [1,2];"));
	}

	@Test
	public void getName() {

		prepare("import Node;").prepareMore(
				"data ZNODE = zf | zf(int) | zf(int,int) | zf(int,int,int);");

		assertTrue(runTestInSameEvaluator("getName(zf()) == \"zf\";"));
		assertTrue(runTestInSameEvaluator("getName(zf(1,2,3)) == \"zf\";"));
	}

	@Test
	public void makeNode() {
		prepare("import Node;");

		assertTrue(runTestInSameEvaluator("{node n = makeNode(\"f\"); getName(n) == \"f\" && arity(n) == 0 && getChildren(n) == []; }"));
		assertTrue(runTestInSameEvaluator("{node n = makeNode(\"f\", 1); getName(n) == \"f\" && arity(n) == 1 && getChildren(n) == [1];}"));
		assertTrue(runTestInSameEvaluator("{node n = makeNode(\"f\", 1, 2); getName(n) == \"f\" && arity(n) == 2 && getChildren(n) == [1,2];}"));
		assertTrue(runTestInSameEvaluator("{node n = makeNode(\"f\", 1, 2, 3); getName(n) == \"f\" && arity(n) == 3 && getChildren(n) == [1,2,3];}"));
	}
	
	private boolean atermWriteRead(String aterm){
		boolean success = false;
		try{
			PrintStream outStream = new PrintStream(new File("xxx"));
			outStream.print(aterm);
			outStream.close();
			prepare("import Node;");
			
			success = runTestInSameEvaluator("{ value N := readATermFromFile(\"xxx\"); N == " + aterm + ";}");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			// Clean up.
			removeTempFile();
		}
		return success;
	}
	
	public void removeTempFile(){
		new File("xxx").delete();
	}
	
	@Test
	public void readATermFromFileInt() {
		assertTrue(atermWriteRead("1"));
	}
	
	@Test
	public void readATermFromFileStr() {
		assertTrue(atermWriteRead("\"abc\""));
	}
	
	@Test
	public void readATermFromFileList() {
		assertTrue(atermWriteRead("[1,2,3]"));
	}
	
	@Test
	public void readATermFromFileFun() {
		assertTrue(atermWriteRead("fn"));
	}

}
