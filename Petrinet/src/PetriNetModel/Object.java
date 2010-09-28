/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PetriNetModel;

import java.math.BigInteger;

import org.eclipse.emf.cdo.CDOObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link PetriNetModel.Object#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see PetriNetModel.PetriNetModelPackage#getObject()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface Object extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(BigInteger)
	 * @see PetriNetModel.PetriNetModelPackage#getObject_Id()
	 * @model id="true"
	 * @generated
	 */
	BigInteger getId();

	/**
	 * Sets the value of the '{@link PetriNetModel.Object#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(BigInteger value);

} // Object
