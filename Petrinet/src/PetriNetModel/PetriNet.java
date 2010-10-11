/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PetriNetModel;

import org.eclipse.emf.cdo.CDOObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Petri Net</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link PetriNetModel.PetriNet#getObjects <em>Objects</em>}</li>
 * </ul>
 * </p>
 *
 * @see PetriNetModel.PetriNetModelPackage#getPetriNet()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface PetriNet extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Objects</b></em>' containment reference list.
	 * The list contents are of type {@link PetriNetModel.PObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Objects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Objects</em>' containment reference list.
	 * @see PetriNetModel.PetriNetModelPackage#getPetriNet_Objects()
	 * @model containment="true"
	 * @generated
	 */
	EList<PObject> getObjects();

} // PetriNet
