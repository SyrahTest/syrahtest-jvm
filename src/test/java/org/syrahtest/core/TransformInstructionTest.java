/*
 *     This file is part of SyrahTest
 *
 *     SyrahTest is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     SyrahTest is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with SyrahTest.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.syrahtest.core;

import org.junit.Test;

/**
 *
 * Created by Nate on 10/2/2015.
 */
public class TransformInstructionTest {

    @Test
    public void literalValue_AlphaNumeric(){
        TransformInstruction i = new TransformInstruction("/xpath/expression", "literalReplacementValue");

    }
}
