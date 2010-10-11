package PetriNetModel.command;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

public class FireTransitionCommand extends CompoundCommand{
	
	protected EditingDomain domain;
	
	public FireTransitionCommand(EditingDomain domain, EObject owner, Command command) {
		super(0);
		this.domain = domain;
		append(command);
	}
	
	public void execute() {
		
	}
}
