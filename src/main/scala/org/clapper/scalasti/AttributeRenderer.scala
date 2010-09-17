/*
  ---------------------------------------------------------------------------
  This software is released under a BSD license, adapted from
  http://opensource.org/licenses/bsd-license.php

  Copyright (c) 2010, Brian M. Clapper
  All rights reserved.

  Redistribution and use in source and binary forms, with or without
  modification, are permitted provided that the following conditions are
  met:

  * Redistributions of source code must retain the above copyright notice,
    this list of conditions and the following disclaimer.

  * Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.

  * Neither the names "clapper.org" nor the names of its contributors may
    be used to endorse or promote products derived from this software
    without specific prior written permission.

  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
  IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
  THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
  ---------------------------------------------------------------------------
*/

package org.clapper.scalasti

import org.antlr.stringtemplate.{AttributeRenderer => ST_AttributeRenderer}
import scala.collection.mutable.{Map => MutableMap}
import java.io.File

/**
 * A more Scala-like StringTemplate attribute renderer. Objects that
 * implement this trait can be registered as attribute renderers with
 * a StringTemplate.
 *
 * @tparam T  the type (class) for which the renderer can render values.
 */
trait AttributeRenderer[T]
{
    private val self = this

    // The actual Java renderer used by StringTemplate.
    private[scalasti] val stRenderer = new ST_AttributeRenderer
    {
        def toString(o: java.lang.Object): String =
            self.toString(o.asInstanceOf[T])

        def toString(o: java.lang.Object, formatName: String): String =
            self.toString(o.asInstanceOf[T], formatName)
    }

    /**
     * Converts an object of type `T` to a string, for inclusion in a template.
     *
     * @param o  the object
     */
    def toString(o: T): String

    /**
     * Converts an object of type `T` to a string, according to a specific
     * format name, for inclusion in a template.
     *
     * @param o           the object
     * @param formatName  the format name
     */
    def toString(o: T, formatName: String): String = toString(o)
}