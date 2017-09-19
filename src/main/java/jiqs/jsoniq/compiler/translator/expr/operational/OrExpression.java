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
 package jiqs.jsoniq.compiler.translator.expr.operational;

import jiqs.semantics.visitor.AbstractExpressionOrClauseVisitor;
import jiqs.jsoniq.compiler.translator.expr.Expression;
import jiqs.jsoniq.compiler.translator.expr.operational.base.NaryExpressionBase;

import java.util.List;


public class OrExpression extends NaryExpressionBase {

    public OrExpression(Expression _mainExpression) {
        super(_mainExpression);

    }

    public OrExpression(Expression _mainExpression, List<Expression> rhs) {
        super(_mainExpression, rhs, Operator.OR);
    }

    @Override
    public String serializationString(boolean prefix){
        String result = (prefix? "(exprSingle " : "") + "(orExpr ";
        result += _mainExpression.serializationString(true);
        if(this.getRightExpressions() != null && this.getRightExpressions().size() > 0)
            for(Expression expr : this.getRightExpressions())
                result += " or " + expr.serializationString(true);
        result += ")";
        return result;
    }

    @Override
    public  <T> T accept(AbstractExpressionOrClauseVisitor<T> visitor, T argument){
        return visitor.visitOrExpr(this, argument);
    }
}