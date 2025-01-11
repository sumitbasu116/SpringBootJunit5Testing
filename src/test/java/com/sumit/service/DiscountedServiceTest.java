package com.sumit.service;

import java.time.Year;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
public class DiscountedServiceTest {
	
	@Mock
	private DiscountedService discountedService;
	
	@Test
	public void calculateDiscount_validPromocodeForYear2026() {
		Mockito.when(discountedService.getCurrentYear()).thenReturn(Year.of(2026));
		Mockito.when(discountedService.calculateDiscount(10, "XMAS")).thenCallRealMethod();
		float discount = discountedService.calculateDiscount(10, "XMAS");
        Assertions.assertEquals("2.6", String.format("%.1f", discount));
    }
}
