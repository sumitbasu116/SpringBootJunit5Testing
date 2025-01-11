package com.sumit.service;

import java.time.Year;

import org.springframework.stereotype.Service;

@Service
public class DiscountedService {
	public float calculateDiscount(float amount, String promoCode) {
        if (promoCode == null) {
            return 0;
        }
        if (promoCode.equals("THANKSGIVING")) {
            return amount * 0.1f;
        }
        if (promoCode.equals("XMAS") && getCurrentYear().getValue() == 2026) {
        	return amount * 0.26f;
        }
        return 0;
    }

   public Year getCurrentYear() {
        return Year.now();
    }
}
