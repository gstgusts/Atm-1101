package com.company.tests;

import com.company.Card;
import org.junit.Assert;
import org.junit.Test;

public class CardTests {

    @Test
    public void is_pin_valid() {
        var card = new Card(1234, "", null);

        var result = card.isPinValid(1234);

        Assert.assertTrue(result);
    }

    @Test
    public void pin_is_invalid() {
        var card = new Card(1234, "", null);

        var result = card.isPinValid(2345);

        Assert.assertFalse(result);
    }
}
