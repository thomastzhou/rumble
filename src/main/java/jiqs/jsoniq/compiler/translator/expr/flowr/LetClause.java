/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Author: Stefan Irimescu
 *
 */
 package jiqs.jsoniq.compiler.translator.expr.flowr;

import jiqs.jsoniq.compiler.translator.expr.ExpressionOrClause;
import jiqs.semantics.visitor.AbstractExpressionOrClauseVisitor;

import java.util.ArrayList;
import java.util.List;

public class LetClause extends FlworClause {

    public List<LetClauseVar> getLetVariables() {
        return letVars;
    }

    public LetClause(List<LetClauseVar> vars)
    {
        super(FLWOR_CLAUSES.LET);
        if(vars == null || vars.isEmpty())
            throw new IllegalArgumentException("Let clause must have at least one variable");
        this.letVars = vars;

    }

    @Override
    public List<ExpressionOrClause> getDescendants(boolean depthSearch) {
        List<ExpressionOrClause> result =  new ArrayList<>();
        letVars.forEach(e -> {
            if (e != null)
                result.add(e);
        });
        return getDescendantsFromChildren(result,depthSearch);
    }

    @Override
    public  <T> T accept(AbstractExpressionOrClauseVisitor<T> visitor, T argument){
        return visitor.visitLetClause(this, argument);
    }

    @Override
    public String serializationString(boolean prefix){
        String result = "(letClause let ";
        for(LetClauseVar var: letVars)
            result += var.serializationString(true)
                    + (letVars.indexOf(var) < letVars.size() -1 ? " , " : "");
        result += ")";
        return result;
    }

    private final List<LetClauseVar> letVars;
}