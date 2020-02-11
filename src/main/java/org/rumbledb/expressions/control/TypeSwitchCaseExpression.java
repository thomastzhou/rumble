package org.rumbledb.expressions.control;


import org.rumbledb.exceptions.ExceptionMetadata;
import org.rumbledb.expressions.Expression;
import org.rumbledb.expressions.Node;
import org.rumbledb.expressions.flowr.FlworVarSequenceType;
import org.rumbledb.expressions.primary.VariableReference;
import sparksoniq.semantics.visitor.AbstractNodeVisitor;

import java.util.ArrayList;
import java.util.List;

public class TypeSwitchCaseExpression extends Expression {

    private VariableReference variableReferenceNode;
    private List<FlworVarSequenceType> union = new ArrayList<>();
    private final Expression returnExpression;

    public TypeSwitchCaseExpression(
            VariableReference var,
            List<FlworVarSequenceType> union,
            Expression returnExpression,
            ExceptionMetadata metadataFromContext
    ) {
        super(metadataFromContext);
        this.variableReferenceNode = var;
        this.union.addAll(union);
        this.returnExpression = returnExpression;
    }

    public VariableReference getVariableReference() {
        return this.variableReferenceNode;
    }

    public List<FlworVarSequenceType> getUnion() {
        return this.union;
    }

    public Expression getReturnExpression() {
        return this.returnExpression;
    }

    @Override
    public List<Node> getChildren() {
        List<Node> result = new ArrayList<>();
        if (this.variableReferenceNode != null)
            result.add(this.variableReferenceNode);
        if (this.union != null && !this.union.isEmpty())
            result.addAll(this.union);
        if (this.returnExpression != null)
            result.add(this.returnExpression);
        return result;
    }

    @Override
    public <T> T accept(AbstractNodeVisitor<T> visitor, T argument) {
        return visitor.visitTypeSwitchCaseExpression(this, argument);
    }

    @Override
    // TODO implement serialization for switch expr
    public String serializationString(boolean prefix) {
        return "";
    }
}