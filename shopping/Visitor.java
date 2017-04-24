package shopping;

import department.BookDepartment;
import department.MusicDepartment;
import department.SoftwareDepartment;
import department.VideoDepartment;

public interface Visitor {
	public void visit (BookDepartment b );
	public void visit (MusicDepartment m);
	public void visit (SoftwareDepartment s);
	public void visit (VideoDepartment v);

}
