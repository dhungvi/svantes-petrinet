/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PetriNetModel.impl;

import PetriNetModel.PObject;
import PetriNetModel.PetriNet;
import PetriNetModel.PetriNetModelPackage;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Petri Net</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link PetriNetModel.impl.PetriNetImpl#getObjects <em>Objects</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PetriNetImpl extends CDOObjectImpl implements PetriNet {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PetriNetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PetriNetModelPackage.Literals.PETRI_NET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<PObject> getObjects() {
		return (EList<PObject>)eGet(PetriNetModelPackage.Literals.PETRI_NET__OBJECTS, true);
	}

} //PetriNetImpl
