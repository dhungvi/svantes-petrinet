/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PetriNetModel.impl;

import PetriNetModel.PetriNetModelPackage;
import PetriNetModel.Place;
import PetriNetModel.Token;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Place</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link PetriNetModel.impl.PlaceImpl#getTokens <em>Tokens</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PlaceImpl extends NodeImpl implements Place {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PetriNetModelPackage.Literals.PLACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Token> getTokens() {
		return (EList<Token>)eGet(PetriNetModelPackage.Literals.PLACE__TOKENS, true);
	}

} //PlaceImpl
