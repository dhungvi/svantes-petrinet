/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PetriNetModel.impl;

import PetriNetModel.Arc;
import PetriNetModel.Node;
import PetriNetModel.PetriNetModelPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Arc</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link PetriNetModel.impl.ArcImpl#getSource <em>Source</em>}</li>
 *   <li>{@link PetriNetModel.impl.ArcImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArcImpl extends ObjectImpl implements Arc {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArcImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PetriNetModelPackage.Literals.ARC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getSource() {
		return (Node)eGet(PetriNetModelPackage.Literals.ARC__SOURCE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Node newSource) {
		eSet(PetriNetModelPackage.Literals.ARC__SOURCE, newSource);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getTarget() {
		return (Node)eGet(PetriNetModelPackage.Literals.ARC__TARGET, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(Node newTarget) {
		eSet(PetriNetModelPackage.Literals.ARC__TARGET, newTarget);
	}

} //ArcImpl
