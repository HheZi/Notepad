package Snake;
import javax.swing.undo.UndoManager;

public class FunctionUndoRedo {
	UndoManager um;
	
	FunctionUndoRedo(UndoManager um){
		this.um = um;
	}
	
	public void undo() {
		try {
			um.undo();
		}catch(Exception a) {}
	}
	
	public void redo() {
		try {
		um.redo();
		}catch(Exception a) {}
	}
}
