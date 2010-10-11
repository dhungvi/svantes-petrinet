/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PetriNetModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link PetriNetModel.Node#getOut <em>Out</em>}</li>
 *   <li>{@link PetriNetModel.Node#getIn <em>In</em>}</li>
 *   <li>{@link PetriNetModel.Node#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see PetriNetModel.PetriNetModelPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends PObject {
	/**
	 * Returns the value of the '<em><b>Out</b></em>' reference list.
	 * The list contents are of type {@link PetriNetModel.Arc}.
	 * It is bidirectional and its opposite is '{@link PetriNetModel.Arc#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out</em>' reference list.
	 * @see PetriNetModel.PetriNetModelPackage#getNode_Out()
	 * @see PetriNetModel.Arc#getSource
	 * @model opposite="source"
	 * @generated
	 */
	EList<Arc> getOut();

	/**
	 * Returns the value of the '<em><b>In</b></em>' reference list.
	 * The list contents are of type {@link PetriNetModel.Arc}.
	 * It is bidirectional and its opposite is '{@link PetriNetModel.Arc#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In</em>' reference list.
	 * @see PetriNetModel.PetriNetModelPackage#getNode_In()
	 * @see PetriNetModel.Arc#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<Arc> getIn();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see PetriNetModel.PetriNetModelPackage#getNode_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link PetriNetModel.Node#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Node
