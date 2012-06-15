package ro.redeul.google.go.inspection;

import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.QuickFix;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import ro.redeul.google.go.GoEditorAwareTestCase;
import ro.redeul.google.go.lang.psi.GoFile;

public class UnusedImportInspectionTest
    extends GoEditorAwareTestCase {
    public void testSimple() throws Exception{ doTest(); }
    public void testOnlyOneImport() throws Exception{ doTest(); }
    public void testBlankImport() throws Exception{ doTest(); }

    @Override
    protected void invoke(Project project, Editor editor, GoFile file) {
        InspectionResult result = new InspectionResult(project);
        new UnusedImportInspection().doCheckFile(file, result, false);
        for (ProblemDescriptor pd : result.getProblems()) {
            QuickFix[] fixes = pd.getFixes();
            assertEquals(1, fixes.length);
            fixes[0].applyFix(project, pd);
        }
    }

    @Override
    protected String getTestDataRelativePath() {
        return "inspection/unusedImport/";
    }
}