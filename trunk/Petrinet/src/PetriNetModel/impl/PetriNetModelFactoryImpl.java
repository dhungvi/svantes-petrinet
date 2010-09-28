/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package PetriNetModel.impl;

import PetriNetModel.Arc;
import PetriNetModel.Node;
import PetriNetModel.PetriNet;
import PetriNetModel.PetriNetModelFactory;
import PetriNetModel.PetriNetModelPackage;
import PetriNetModel.Place;
import PetriNetModel.Token;
import PetriNetModel.Transition;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PetriNetModelFactoryImpl extends EFactoryImpl implements PetriNetModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PetriNetModelFactory init() {
		try {
			PetriNetModelFactory thePetriNetModelFactory = (PetriNetModelFactory)EPackage.Registry.INSTANCE.getEFactory("dk.dtu.s083143"); 
			if (thePetriNetModelFactory != null) {
				return thePetriNetModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PetriNetModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PetriNetModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case PetriNetModelPackage.PETRI_NET: return (EObject)createPetriNet();
			case PetriNetModelPackage.OBJECT: return (EObject)createObject();
			case PetriNetModelPackage.NODE: return (EObject)createNode();
			case PetriNetModelPackage.TRANSITION: return (EObject)createTransition();
			case PetriNetModelPackage.PLACE: return (EObject)createPlace();
			case PetriNetModelPackage.ARC: return (EObject)createArc();
			case PetriNetModelPackage.TOKEN: return (EObject)createToken();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PetriNet createPetriNet() {
		PetriNetImpl petriNet = new PetriNetImpl();
		return petriNet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PetriNetModel.Object createObject() {
		ObjectImpl object = new ObjectImpl();
		return object;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node createNode() {
		NodeImpl node = new NodeImpl();
		return node;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transition createTransition() {
		TransitionImpl transition = new TransitionImpl();
		return transition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Place createPlace() {
		PlaceImpl place = new PlaceImpl();
		return place;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Arc createArc() {
		ArcImpl arc = new ArcImpl();
		return arc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Token createToken() {
		TokenImpl token = new TokenImpl();
		return token;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PetriNetModelPackage getPetriNetModelPackage() {
		return (PetriNetModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PetriNetModelPackage getPackage() {
		return PetriNetModelPackage.eINSTANCE;
	}

} //PetriNetModelFactoryImpl
