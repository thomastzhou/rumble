/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authors: Stefan Irimescu, Can Berker Cikis
 *
 */

package sparksoniq.semantics.types;


import java.io.Serializable;

public class ItemType implements Serializable {


    private static final long serialVersionUID = 1L;
    private ItemTypes type;

    public ItemType() {
    }

    public ItemType(ItemTypes type) {
        this.type = type;

    }

    public ItemTypes getType() {
        return this.type;
    }

    public boolean isSubtypeOf(ItemType superType) {
        if (superType.getType() == ItemTypes.Item)
            return true;
        if (superType.getType() == ItemTypes.JSONItem) {
            if (
                this.type == ItemTypes.ObjectItem
                    || this.type == ItemTypes.ArrayItem
                    || this.type == ItemTypes.JSONItem
                    || this.type == ItemTypes.NullItem
            )
                return true;
            return false;
        }

        if (superType.getType() == ItemTypes.AtomicItem) {
            if (
                this.type == ItemTypes.StringItem
                    || this.type == ItemTypes.IntegerItem
                    || this.type == ItemTypes.DecimalItem
                    || this.type == ItemTypes.DoubleItem
                    || this.type == ItemTypes.BooleanItem
            )
                return true;
            return false;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ItemType))
            return false;
        ItemType itemType = (ItemType) o;
        return this.getType().equals(itemType.getType());
    }
}
