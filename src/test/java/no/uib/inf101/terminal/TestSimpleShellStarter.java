package no.uib.inf101.terminal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSimpleShellStarter {
    static final String orgCwd = System.getProperty("user.dir");
    static final String DIR = "testdir";
    static final String SUBDIR = "subdir";
    private File dir;
    private File subdir;
    private SimpleShell shell;

    ////////////////////////////////////////////////////////////////////////
    //////// The tests  ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////

    @Test
    public void testDoNothing() {
        assertEquals("$ ", shell.whatTheScreenLooksLike());
    }

    @Test
    public void testWriteFoo() {
        shell.aKeyIsPressed('f');
        shell.aKeyIsPressed('o');
        shell.aKeyIsPressed('o');

        assertEquals("$ foo", shell.whatTheScreenLooksLike());
    }

    @Test
    public void testIllegalCommand() {
        shell.aKeyIsPressed('f');
        shell.aKeyIsPressed('o');
        shell.aKeyIsPressed('o');
        shell.aKeyIsPressed('\n');

        assertEquals("$ foo\nCommand not found: \"foo\"\n$ ", shell.whatTheScreenLooksLike());
    }

    @Test
    public void testPwd() throws IOException {
        shell.aKeyIsPressed('p');
        shell.aKeyIsPressed('w');
        shell.aKeyIsPressed('d');
        shell.aKeyIsPressed('\n');

        String expected = "$ pwd" + "\n" + this.dir.getCanonicalPath() + "\n$ ";
        assertEquals(expected, shell.whatTheScreenLooksLike());
    }

    @Test
    public void testLs() throws IOException {
        shell.aKeyIsPressed('l');
        shell.aKeyIsPressed('s');
        shell.aKeyIsPressed('\n');

        String expected = "$ ls\n" + SUBDIR + " \n$ ";
        assertEquals(expected, shell.whatTheScreenLooksLike());
    }

    @Test
    public void testCd() throws IOException {
        shell.aKeyIsPressed('p');
        shell.aKeyIsPressed('w');
        shell.aKeyIsPressed('d');
        shell.aKeyIsPressed('\n');

        shell.aKeyIsPressed('c');
        shell.aKeyIsPressed('d');
        shell.aKeyIsPressed(' ');
        shell.aKeyIsPressed('s');
        shell.aKeyIsPressed('u');
        shell.aKeyIsPressed('b');
        shell.aKeyIsPressed('d');
        shell.aKeyIsPressed('i');
        shell.aKeyIsPressed('r');
        shell.aKeyIsPressed('\n');

        shell.aKeyIsPressed('p');
        shell.aKeyIsPressed('w');
        shell.aKeyIsPressed('d');
        shell.aKeyIsPressed('\n');


        String expected = "$ pwd\n"
            + this.dir.getCanonicalPath() + "\n"
            + "$ cd subdir\n"
            + "$ pwd\n"
            + this.subdir.getCanonicalPath() + "\n"
            + "$ ";

        assertEquals(expected, shell.whatTheScreenLooksLike());
    }


    ////////////////////////////////////////////////////////////////////////
    //////// Preparing the tests  //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////

    @BeforeEach
    public void setUp() throws IOException {
        // Set up test directory
        File cwd = new File(orgCwd);
        this.dir = new File(cwd, DIR);
        this.subdir = new File(this.dir, SUBDIR);
        this.dir.mkdir();
        this.subdir.mkdir();
        System.setProperty("user.dir", this.dir.getCanonicalPath());
        this.shell = new SimpleShell();
    }

    @AfterEach
    public void tearDown() {
        File cwd = new File(System.getProperty("user.dir"));
        File testDir = new File(cwd, DIR);
        deleteFolderAndItsContent(testDir);
        System.setProperty("user.dir", orgCwd);
        this.shell = null;
    }
    private void deleteFolderAndItsContent(File file) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                deleteFolderAndItsContent(f);
            }
        }
        file.delete();
    }

}
