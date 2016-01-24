/*
 * The MIT License
 *
 * Copyright 2016 Romain Porte (MicroJoe) microjoe at mailoo.org.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fr.licornesduswag.hcode.util;

import org.junit.Test;

import fr.licornesduswag.hcode.utils.LinearInterpolate;
import fr.licornesduswag.hcode.utils.Point;

import static org.junit.Assert.*;

/**
 *
 * @author Romain Porte (MicroJoe) microjoe at mailoo.org
 */
public class LinearInterpolateTest {
    
    public LinearInterpolateTest() {
    }

    @Test
    public void testInterpolate() {
        Point a = new Point(100, 100);
        Point b = new Point(0, 0);
        
        Point result0 = LinearInterpolate.interpolate(a, b, 0);
        
        assertEquals(result0.getX(), a.getX());
        assertEquals(result0.getY(), a.getY());
        
        Point result50 = LinearInterpolate.interpolate(a, b, 0.5);
        
        assertEquals(result50.getX(), 50);
        assertEquals(result50.getY(), 50);
        
        Point result100 = LinearInterpolate.interpolate(a, b, 1);
        
        assertEquals(result100.getX(), b.getX());
        assertEquals(result100.getY(), b.getY());
    }
    
    @Test
    public void testNegativeInterpolate() {
        Point a = new Point(-100, -100);
        Point b = new Point(100, 100);
        
        Point result50 = LinearInterpolate.interpolate(a, b, 0.5);
        
        assertEquals(result50.getX(), 0);
        assertEquals(result50.getY(), 0);
    }
    
}
