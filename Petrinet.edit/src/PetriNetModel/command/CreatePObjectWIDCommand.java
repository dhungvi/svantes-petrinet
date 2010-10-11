package PetriNetModel.command;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import PetriNetModel.PObject;
import PetriNetModel.PetriNetModelPackage;
import PetriNetModel.PetriNet;

/**
 * Command that adds a PObject to a Petrinet and sets its ID to
 * a unique value. This is the solution to assignment 3 in the
 * tutorial of the SE2 course.
 * 
 * @author Ekkart Kindler
 * 
 */
public class CreatePObjectWIDCommand extends CompoundCommand {
   
	protected EditingDomain domain;
	
	public CreatePObjectWIDCommand(EditingDomain domain,
			EObject owner,
			Command command) {
		super(0);
		this.domain = domain;
		
		append(command);
	}
	
	public void execute() {
		// A correct ID can only be created when the command is executed.
		// To this end, we calculate a unique id (max +1) and set it
		// by the appendAndExecute method.
		super.execute();
		Collection<?> result = super.getResult();
		int max = -1;
		for (Object o: result) {
			if (o instanceof PetriNetModel.PObject) {
				PetriNetModel.PObject po = (PetriNetModel.PObject) o;
				if (max < 0) {
					max = 0;
					EObject container = po.eContainer();
					if (container instanceof PetriNet)
					  for (PObject pobject: ((PetriNet) po.eContainer()).getObjects()) {
						max = Math.max(max, pobject.getId());
					}
				} 
				max = max + 1;

				appendAndExecute(new SetCommand(domain, po,
						PetriNetModelPackage.eINSTANCE.getPObject_Id(), max));
			}
			
		}

	}

}
