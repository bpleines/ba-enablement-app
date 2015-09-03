package com.redhat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

public class DroolsTest {

	private StatelessDecisionService service = BrmsHelper.newStatelessDecisionServiceBuilder().auditLogName("audit").build();

	@Test
	public void helloWorldTest() {
		// given
		Collection<Object> facts = new ArrayList<Object>();
		Business business = new Business();
		business.setName("test");
		facts.add(business);

		// when
		RuleResponse response = service.runRules(facts, "defaultPackage.Process", RuleResponse.class);

		// then
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getBusiness());
		Assert.assertEquals("test", response.getBusiness().getName());
	}
/*
	@Test
	public void shouldFilterOutAllRequestsFromKansas(){
		// scenario: business from Kansas are handled by another system - filter them out
		// given a business from Kansas
		Collection<Object> facts = new ArrayList<Object>();
		Business business = new Business();
		business.setName("Victory");
		business.setAddressLine1("512 Cobbleskill Lane");
		business.setAddressLine2("Exton KY, 19341");
		business.setPhoneNumber("555-5555-5555");
		business.setCity("Exton");
		business.setStateCode("KS");
		business.setZipCode("19341");
		business.setFederalTaxId("00000");
		facts.add(business);
		
		// when I apply the filtering rules
		RuleResponse response = service.runRules(facts, "VerifySupplier", RuleResponse.class);
		
		// then the business should be filtered
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResponseCode());
		Assert.assertEquals("filtered", response.getResponseCode());
		
		// and the reason message should be "business filtered from Kansas"
		boolean found = false;
		for (Reason reason : response.getReasons()){
			if ( reason.getReasonMessage().equals( "business filtered from Kansas") ){
				found = true;
			}
		}
		Assert.assertTrue( "business filtered from Kansas", found );
	}
	
	@Test
	public void shouldProcessAllBusinessesNotFromKansas(){
		// scenario: we are responsible for all businesses not from Kansas
		// given a business from Pennsylvania
		Collection<Object> facts = new ArrayList<Object>();
		Business business = new Business();
		business.setStateCode("PA");
		business.setZipCode("19341");
		business.setAddressLine1("512 Cobbleskill Lane");
		facts.add(business);
		// when I apply the filtering rules
		RuleResponse response = service.runRules(facts, "VerifySupplier", RuleResponse.class);
		// then the business should be not be filtered
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getBusiness());
		Assert.assertEquals("PA", response.getBusiness().getStateCode());
		Assert.assertNotEquals("filtered", response.getResponseCode());
		// and the validation rules should be applied to the business
				
	}
	
	@Test
	public void shouldCreateValidationErrorsForAnyFieldThatAreEmptyOrNull(){
		// scenario: all fields must have values. 
		// given a business 
		
		Collection<Object> facts = new ArrayList<Object>();
		Business business = new Business();
		
		// and the business' zipcode is empty
		business.setZipCode(null);
		// and the business' address line 1 is null
		business.setAddressLine1(null);
		facts.add(business);
		// when I apply the validation rules
		RuleResponse response = service.runRules(facts, "VerifySupplier", RuleResponse.class);
		// then the business should be return a validation error
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getResponseCode());
		Assert.assertEquals("validation error", response.getResponseCode());
		// establish boolean for separate testing
		boolean found = false;
		for (Reason reason : response.getReasons()){
			if ( reason.getReasonMessage().equals( "no zipCode") ){
				found = true;
			}
		}
		Assert.assertTrue( "no ZipCode", found );
		// and a message should say the address is null
		found = false;
		for (Reason reason : response.getReasons()){
			if ( reason.getReasonMessage().equals( "no addressLine1") ){
				found = true;
			}
		}
	}
	
	@Test
	public void shouldEnrichTheTaxIdWithZipCode(){
		// scenario: we need to enrich the taxId with the zipcode for system XYZ
		// given a business 
		
		Collection<Object> facts = new ArrayList<Object>();
		Business business = new Business();
		// give address to pass validating step
		business.setStateCode("PA");
		business.setAddressLine1("512 Cobbleskill Lane");
		// and the business' zipcode is 10002
		business.setZipCode("10002");
		// and the business' taxId is 98765
		business.setFederalTaxId("98765");
		facts.add(business);
		// when I apply the enrichment rules
		RuleResponse response = service.runRules(facts, "VerifySupplier", RuleResponse.class);
		// then the business' taxId should be enriched to 98765-10002
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getBusiness().getFederalTaxId());
		Assert.assertNotNull(response.getBusiness().getZipCode());
		Assert.assertEquals("98765-10002", response.getBusiness().getFederalTaxId());
		
		//check that the responseCode is enriched
		Assert.assertNotNull(response.getResponseCode());
		Assert.assertEquals("enriched", response.getResponseCode());
		
	}
	*/
}
