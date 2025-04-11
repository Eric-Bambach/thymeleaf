/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2025 Thymeleaf (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.examples.spring6.extrathyme.business.util;

import org.thymeleaf.examples.spring6.extrathyme.business.entities.Remark;



public class RemarkUtil {


    /*
     * Based on position, assign the appropriate remark
     * The team in the first position of the repository will have the
     * Remark.WORLD_CHAMPIONS_LEAGUE, the second and third teams will have the
     * Remark.CONTINENTAL_PLAYOFFS, the teams not in the final position will
     * have the Remark.AUDIENCE, while the last team has the Remark.RELEGATION.
     */
    public static Remark GetRemark(Integer position, Integer intLastPosition)
    {
        Remark remark = null;

        // The first "if" is deliberately kept empty, the remark variable remains
        // null in those cases.
        //noinspection StatementWithEmptyBody
        if (position == null)
        {
        }
        else if (position == 1)
        {
            remark = Remark.WORLD_CHAMPIONS_LEAGUE;
        }
        /*
        * If a team has been determined to not be in the first position, and
        * is also determined to not be in the last position, check if the team
        * is in either the second or third positions.
        */
        else if(!position.equals(intLastPosition))
        {
            if (position == 2 || position == 3)
            {
            remark = Remark.CONTINENTAL_PLAYOFFS;
            }
            else
            {
                remark = Remark.AUDIENCE;
            }
        }
        else
        {
            remark = Remark.RELEGATION;
        }


        return remark;
    }
    

    private RemarkUtil() {
        super();
    }
    
}
