/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

/*
* Jopr Management Platform
* Copyright (C) 2005-2009 Red Hat, Inc.
* All rights reserved.
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License, version 2, as
* published by the Free Software Foundation, and/or the GNU Lesser
* General Public License, version 2.1, also as published by the Free
* Software Foundation.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License and the GNU Lesser General Public License
* for more details.
*
* You should have received a copy of the GNU General Public License
* and the GNU Lesser General Public License along with this program;
* if not, write to the Free Software Foundation, Inc.,
* 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
*/
package org.rhq.plugins.jbossas5.adapter.impl.configuration;

import org.rhq.core.domain.configuration.PropertySimple;
import org.rhq.core.domain.configuration.definition.PropertyDefinitionSimple;
import org.rhq.plugins.jbossas5.adapter.api.AbstractPropertySimpleAdapter;
import org.rhq.plugins.jbossas5.adapter.api.PropertyAdapter;

import org.jboss.metatype.api.types.EnumMetaType;
import org.jboss.metatype.api.types.MetaType;
import org.jboss.metatype.api.values.EnumValue;
import org.jboss.metatype.api.values.EnumValueSupport;
import org.jboss.metatype.api.values.MetaValue;

/**
 * This class provides code that maps back and forth between a {@link PropertySimple} and an {@link EnumValueSupport}.
 *
 * @author Ian Springer
 */
public class PropertySimpleToEnumValueAdapter extends AbstractPropertySimpleAdapter implements PropertyAdapter<PropertySimple, PropertyDefinitionSimple>
{
    public void populatePropertyFromMetaValue(PropertySimple propSimple, MetaValue metaValue, PropertyDefinitionSimple propDefSimple)
    {
        Object value = (metaValue != null) ? ((EnumValue)metaValue).getValue() : null;
        propSimple.setValue(value);
    }

    public MetaValue convertToMetaValue(PropertySimple propSimple, PropertyDefinitionSimple propDefSimple, MetaType metaType)
    {
        EnumValue enumValue = new EnumValueSupport((EnumMetaType)metaType, propSimple.getStringValue());
        populateMetaValueFromProperty(propSimple, enumValue, propDefSimple);
        return enumValue;
    }

    protected void setInnerValue(String propSimpleValue, MetaValue metaValue, PropertyDefinitionSimple propDefSimple)
    {
        EnumValueSupport enumValueSupport = (EnumValueSupport)metaValue;
        enumValueSupport.setValue(propSimpleValue);
    }
}