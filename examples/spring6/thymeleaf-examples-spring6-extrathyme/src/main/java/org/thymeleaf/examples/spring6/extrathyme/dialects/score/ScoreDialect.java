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
package org.thymeleaf.examples.spring6.extrathyme.dialects.score;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.examples.spring6.extrathyme.business.entities.Team;
import org.thymeleaf.examples.spring6.extrathyme.business.entities.repositories.TeamRepository;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

public class ScoreDialect extends AbstractProcessorDialect {

    private static final String DIALECT_NAME = "Score Dialect";


    public ScoreDialect() {
        // We will set this dialect the same "dialect processor" precedence as
        // the Standard Dialect, so that processor executions can interleave.
        super(DIALECT_NAME, "score", StandardDialect.PROCESSOR_PRECEDENCE);
    }

    /*
     * Two attribute processors are declared: 'classforposition' and
     * 'remarkforposition'. Also one element processor: the 'headlines'
     * tag.
     */
    public Set<IProcessor> getProcessors(final String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<IProcessor>();

        /* The original project had hardcoded switch statements to handle remarks
        * and the relevant css.
        * This calls to a procedure to find the last team listed in the mock
        * repository to dynamically handle that team.
        */
        Integer intLastPosition = 0;
        intLastPosition = GetLastPosition();

        processors.add(new ClassForPositionAttributeTagProcessor(dialectPrefix, intLastPosition));
        processors.add(new RemarkForPositionAttributeTagProcessor(dialectPrefix, intLastPosition));
        processors.add(new HeadlinesElementTagProcessor(dialectPrefix));
        processors.add(new MatchDayTodayModelProcessor(dialectPrefix));
        // This will remove the xmlns:score attributes we might add for IDE validation
        processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
        return processors;
    }

    /*
     * Using a List, find the last position
     */
    private Integer GetLastPosition()
    {
        List<Team> Teams = new TeamRepository().findAllTeams();
        Integer intlastPosition = 0;

        for(Team team : Teams)
        {
            intlastPosition++;
        }

        return intlastPosition;
    }


}
