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
 * SmileSVR.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.classifiers.smile.regression;

import smile.data.AttributeDataset;
import smile.regression.Regression;
import smile.regression.SVR;
import weka.classifiers.AbstractSmileRegressor;
import weka.classifiers.smile.math.kernel.AbstractSmileKernel;
import weka.classifiers.smile.math.kernel.SmileGaussianKernel;
import weka.core.Capabilities;
import weka.core.Capabilities.Capability;
import weka.core.WekaOptionUtils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * SMILE SVR.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmileSVR
  extends AbstractSmileRegressor {

  private static final long serialVersionUID = -8861088582494627633L;

  public static final String KERNEL = "kernel";

  public static final String EPSILON = "epsilon";

  public static final String CAPACITY = "capacity";

  public static final String TOLERANCE = "tolerance";

  /** the kernel. */
  protected AbstractSmileKernel m_Kernel = getDefaultKernel();

  /** the epsilon. */
  protected double m_Epsilon = getDefaultEpsilon();

  /** the capacity. */
  protected double m_Capacity = getDefaultCapacity();

  /** the tolerance. */
  protected double m_Tolerance = getDefaultTolerance();

  /**
   * Returns a description of the classifier.
   *
   * @return the description
   */
  @Override
  public String globalInfo() {
    return "Support vector regression. Like SVMs for classification, the model "
      + "produced by SVR depends only on a subset of the training data, because "
      + "the cost function ignores any training data close to the model "
      + "prediction (within a threshold ε).\n\n"
      + "See also:\n"
      + "https://haifengl.github.io/smile/api/java/smile/regression/SVR.html";
  }

  /**
   * Returns an enumeration describing the available options.
   *
   * @return an enumeration of all the available options.
   */
  @Override
  public Enumeration listOptions() {
    Vector result = new Vector();
    WekaOptionUtils.addOption(result, kernelTipText(), "" + getDefaultKernel(), KERNEL);
    WekaOptionUtils.addOption(result, epsilonTipText(), "" + getDefaultEpsilon(), EPSILON);
    WekaOptionUtils.addOption(result, capacityTipText(), "" + getDefaultCapacity(), CAPACITY);
    WekaOptionUtils.addOption(result, toleranceTipText(), "" + getDefaultTolerance(), TOLERANCE);
    WekaOptionUtils.add(result, super.listOptions());
    return WekaOptionUtils.toEnumeration(result);
  }

  /**
   * Parses a given list of options.
   *
   * @param options the list of options as an array of strings
   * @throws Exception if an option is not supported
   */
  @Override
  public void setOptions(String[] options) throws Exception {
    setKernel((AbstractSmileKernel) WekaOptionUtils.parse(options, KERNEL, getDefaultKernel()));
    setEpsilon(WekaOptionUtils.parse(options, EPSILON, getDefaultEpsilon()));
    setCapacity(WekaOptionUtils.parse(options, CAPACITY, getDefaultCapacity()));
    setTolerance(WekaOptionUtils.parse(options, TOLERANCE, getDefaultTolerance()));
    super.setOptions(options);
  }

  /**
   * Gets the current settings.
   *
   * @return an array of strings suitable for passing to setOptions
   */
  @Override
  public String[] getOptions() {
    List<String> result = new ArrayList<String>();
    WekaOptionUtils.add(result, KERNEL, getKernel());
    WekaOptionUtils.add(result, EPSILON, getEpsilon());
    WekaOptionUtils.add(result, CAPACITY, getCapacity());
    WekaOptionUtils.add(result, TOLERANCE, getTolerance());
    WekaOptionUtils.add(result, super.getOptions());
    return WekaOptionUtils.toArray(result);
  }

  /**
   * The default kernel.
   *
   * @return		the default
   */
  protected AbstractSmileKernel getDefaultKernel() {
    return new SmileGaussianKernel();
  }

  /**
   * Sets the kernel.
   *
   * @param value	the kernel
   */
  public void setKernel(AbstractSmileKernel value) {
    m_Kernel = value;
    reset();
  }

  /**
   * Returns the kernel.
   *
   * @return		the kernel
   */
  public AbstractSmileKernel getKernel() {
    return m_Kernel;
  }

  /**
   * Returns the help string.
   *
   * @return		the help string
   */
  public String kernelTipText() {
    return "The kernel to use.";
  }

  /**
   * The default epsilon.
   *
   * @return		the default
   */
  protected double getDefaultEpsilon() {
    return 1e-3;
  }

  /**
   * Sets the epsilon.
   *
   * @param value	the epsilon (>= 0)
   */
  public void setEpsilon(double value) {
    if (value >= 0.0) {
      m_Epsilon = value;
      reset();
    }
  }

  /**
   * Returns the epsilon.
   *
   * @return		the epsilon (>= 0)
   */
  public double getEpsilon() {
    return m_Epsilon;
  }

  /**
   * Returns the help string.
   *
   * @return		the help string
   */
  public String epsilonTipText() {
    return "The epsilon parameter.";
  }

  /**
   * The default capacity.
   *
   * @return		the default
   */
  protected double getDefaultCapacity() {
    return 1.0;
  }

  /**
   * Sets the capacity.
   *
   * @param value	the capacity (>= 0)
   */
  public void setCapacity(double value) {
    if (value >= 0.0) {
      m_Capacity = value;
      reset();
    }
  }

  /**
   * Returns the capacity.
   *
   * @return		the capacity (>= 0)
   */
  public double getCapacity() {
    return m_Capacity;
  }

  /**
   * Returns the help string.
   *
   * @return		the help string
   */
  public String capacityTipText() {
    return "The capacity parameter.";
  }

  /**
   * The default tolerance.
   *
   * @return		the default
   */
  protected double getDefaultTolerance() {
    return 0.001;
  }

  /**
   * Sets the tolerance.
   *
   * @param value	the tolerance (> 0)
   */
  public void setTolerance(double value) {
    if (value > 0.0) {
      m_Tolerance = value;
      reset();
    }
  }

  /**
   * Returns the tolerance.
   *
   * @return		the tolerance (> 0)
   */
  public double getTolerance() {
    return m_Tolerance;
  }

  /**
   * Returns the help string.
   *
   * @return		the help string
   */
  public String toleranceTipText() {
    return "The tolerance parameter.";
  }

  /**
   * Returns default capabilities of the classifier.
   *
   * @return the capabilities of this classifier
   */
  @Override
  public Capabilities getCapabilities() {
    Capabilities result = super.getCapabilities();
    result.disableAll();

    // attributes
    result.enable(Capability.NOMINAL_ATTRIBUTES);
    result.enable(Capability.NUMERIC_ATTRIBUTES);
    result.enable(Capability.DATE_ATTRIBUTES);
    result.enable(Capability.MISSING_VALUES);

    // class
    result.enable(Capability.NUMERIC_CLASS);
    result.enable(Capability.DATE_CLASS);
    result.enable(Capability.MISSING_CLASS_VALUES);

    return result;
  }

  /**
   * Builds the classifier.
   *
   * @param data	the data to use for training
   * @return 		the generated model
   * @throws Exception	if training fails or data does not match capabilities
   */
  @Override
  protected Regression<double[]> buildClassifier(AttributeDataset data) throws Exception {
    return new SVR(data.x(), data.y(), m_Kernel.getKernel(), m_Epsilon, m_Capacity, m_Tolerance);
  }

  /**
   * Executes the classifier from the commandline.
   *
   * @param args	the options
   */
  public static void main(String[] args) {
    runClassifier(new SmileSVR(), args);
  }
}
