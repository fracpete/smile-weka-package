/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * SmileDatasetUtils.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.core;

import smile.data.Attribute;
import smile.data.AttributeDataset;
import smile.data.DateAttribute;
import smile.data.NominalAttribute;
import smile.data.NumericAttribute;
import smile.data.StringAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for dealing with SMILE datasets.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmileDatasetUtils {

  /**
   * Converts a Weka Instances to a SMILE Dataset.
   *
   * @param inst	the data to convert
   * @return		the generated dataset
   * @throws Exception	if conversion fails, eg unsupported attribute type or parsing error
   */
  public static AttributeDataset convertInstances(Instances inst) throws Exception {
    AttributeDataset	result;
    List<Attribute> 	atts;
    int			i;
    int			n;
    weka.core.Attribute	att;
    Attribute		newAtt;
    Attribute		clsAtt;
    List<String> 	labels;
    int			clsIdx;

    // attributes
    atts   = new ArrayList<Attribute>();
    clsAtt = null;
    clsIdx = inst.classIndex();
    for (i = 0; i < inst.numAttributes(); i++) {
      att = inst.attribute(i);
      switch (att.type()) {
	case weka.core.Attribute.NUMERIC:
	  newAtt = new NumericAttribute(att.name(), att.weight());
	  break;
	case weka.core.Attribute.DATE:
	  newAtt = new DateAttribute(att.name(), null, att.weight(), att.getDateFormat());
	  break;
	case weka.core.Attribute.NOMINAL:
	  labels = new ArrayList<String>();
	  for (n = 0; n < att.numValues(); n++)
	    labels.add(att.value(n));
	  newAtt = new NominalAttribute(att.name(), att.weight(), labels.toArray(new String[0]));
	  break;
	case weka.core.Attribute.STRING:
	  newAtt = new StringAttribute(att.name(), att.weight());
	  break;
	default:
	  throw new IllegalArgumentException(
	    "Unhandled attribute type (#" + (i+1) + "/" + att.name() + "): "
	      + weka.core.Attribute.typeToString(att.type()));
      }
      if (i == clsIdx)
	clsAtt = newAtt;
      else
	atts.add(newAtt);
    }

    if (clsIdx == -1)
      result = new AttributeDataset(inst.relationName(), atts.toArray(new Attribute[0]));
    else
      result = new AttributeDataset(inst.relationName(), atts.toArray(new Attribute[0]), clsAtt);

    // data
    for (n = 0; n < inst.numInstances(); n++) {
      if (clsIdx == -1)
        result.add(convertInstance(inst.instance(n), result));
      else
        result.add(convertInstance(inst.instance(n), result), convertClassValue(inst.instance(n), result));
      convertInstance(inst.instance(n), result);
    }

    return result;
  }

  /**
   * Turns the Weka Instance into a double array (excl class).
   *
   * @param in		the instance to convert
   * @param dataset 	the dataset structure to generate the row for
   * @return		the generated values (excl class)
   * @throws Exception	if conversion fails, eg unsupported attribute type or parsing error
   */
  public static double[] convertInstance(Instance in, AttributeDataset dataset) throws Exception {
    double[] 	result;
    int		clsIdx;
    int		j;
    int		i;

    clsIdx = in.classIndex();
    result = new double[(clsIdx == -1) ? in.numAttributes() : in.numAttributes() - 1];
    j      = 0;
    for (i = 0; i < in.numAttributes(); i++) {
      if (i == clsIdx)
	continue;
      result[j] = Utils.missingValue();
      switch (in.attribute(i).type()) {
	case weka.core.Attribute.NUMERIC:
	  result[j] = in.value(i);
	  break;
	case weka.core.Attribute.DATE:
	case weka.core.Attribute.NOMINAL:
	case weka.core.Attribute.STRING:
	  result[j] = dataset.attributes()[i].valueOf(in.stringValue(i));
	  break;
	default:
	  throw new IllegalArgumentException(
	    "Unhandled attribute type (#" + (i+1) + "/" + in.attribute(i).name() + "): "
	      + weka.core.Attribute.typeToString(in.attribute(i).type()));
      }
      j++;
    }

    return result;
  }

  /**
   * Turns the Weka class value into an internal SMILE value using the specified dataset.
   *
   * @param in		the instance to convert
   * @param dataset 	the dataset structure to generate the row for
   * @return		the generated class value
   * @throws Exception	if conversion fails, eg unsupported attribute type or parsing error
   */
  public static double convertClassValue(Instance in, AttributeDataset dataset) throws Exception {
    int		clsIdx;

    clsIdx = in.classIndex();
    switch (in.attribute(clsIdx).type()) {
      case weka.core.Attribute.NUMERIC:
	return in.classValue();
      case weka.core.Attribute.DATE:
      case weka.core.Attribute.NOMINAL:
      case weka.core.Attribute.STRING:
	return dataset.responseAttribute().valueOf(in.stringValue(clsIdx));
      default:
	throw new IllegalArgumentException(
	  "Unhandled attribute type (#" + (in.classIndex()+1) + "/" + in.classAttribute().name() + "): "
	    + weka.core.Attribute.typeToString(in.classAttribute().type()));
    }
  }
}
