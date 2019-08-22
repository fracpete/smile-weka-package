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
 * SmileSVRBase.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package smile.regression;

import weka.core.Utils;
import weka.core.WekaOptionUtils;
import smile.regression.AbstractSmileRegressor;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import smile.math.kernel.AbstractSmileKernel;

/**
 * Superclass for SmileSVR containing the option handling.
 *
 * @author FracPete
 */
public abstract class SmileSVRBase
  extends AbstractSmileRegressor {

  /** the flag for {@link #m_Kernel}. */
  public final static String KERNEL = "kernel";

  /** the flag for {@link #m_Epsilon}. */
  public final static String EPSILON = "epsilon";

  /** the flag for {@link #m_Capacity}. */
  public final static String CAPACITY = "capacity";

  /** the flag for {@link #m_Tolerance}. */
  public final static String TOLERANCE = "tolerance";

  /** the kernel to use. */
  protected AbstractSmileKernel m_Kernel = getDefaultKernel();

  /** the epsilon parameter. */
  protected double m_Epsilon = getDefaultEpsilon();

  /** the capacity parameter. */
  protected double m_Capacity = getDefaultCapacity();

  /** the convergence tolerance. */
  protected double m_Tolerance = getDefaultTolerance();

  /**
   * Returns a desription of the class.
   *
   * @return the description
   */
  public abstract String globalInfo();

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
   * The default value for kernel.
   *
   * @return the default value
   * @see #m_Kernel
   */
  protected AbstractSmileKernel getDefaultKernel() {
    return new smile.math.kernel.SmileGaussianKernel();
  }

  /**
   * Returns the kernel to use.
   *
   * @return the current value
   * @see #m_Kernel
   */
  public AbstractSmileKernel getKernel() {
    return m_Kernel;
  }

  /**
   * Sets the kernel to use.
   *
   * @param value the new value
   * @see #m_Kernel
   */
  public void setKernel(AbstractSmileKernel value) {
    m_Kernel = value;
  }

  /**
   * Returns the help string for kernel.
   *
   * @return the help string
   * @see #m_Kernel
   */
  public String kernelTipText() {
    return "The kernel to use.";
  }

  /**
   * The default value for epsilon.
   *
   * @return the default value
   * @see #m_Epsilon
   */
  protected double getDefaultEpsilon() {
    return 1e-3;
  }

  /**
   * Returns the epsilon parameter.
   *
   * @return the current value (value >= 0.0)
   * @see #m_Epsilon
   */
  public double getEpsilon() {
    return m_Epsilon;
  }

  /**
   * Sets the epsilon parameter.
   *
   * @param value the new value (value >= 0.0)
   * @see #m_Epsilon
   */
  public void setEpsilon(double value) {
    if (value >= 0.0) {
      m_Epsilon = value;
    }
  }

  /**
   * Returns the help string for epsilon.
   *
   * @return the help string
   * @see #m_Epsilon
   */
  public String epsilonTipText() {
    return "The epsilon parameter; value >= 0.0.";
  }

  /**
   * The default value for capacity.
   *
   * @return the default value
   * @see #m_Capacity
   */
  protected double getDefaultCapacity() {
    return 1.0;
  }

  /**
   * Returns the capacity parameter.
   *
   * @return the current value (value >= 0.0)
   * @see #m_Capacity
   */
  public double getCapacity() {
    return m_Capacity;
  }

  /**
   * Sets the capacity parameter.
   *
   * @param value the new value (value >= 0.0)
   * @see #m_Capacity
   */
  public void setCapacity(double value) {
    if (value >= 0.0) {
      m_Capacity = value;
    }
  }

  /**
   * Returns the help string for capacity.
   *
   * @return the help string
   * @see #m_Capacity
   */
  public String capacityTipText() {
    return "The capacity parameter; value >= 0.0.";
  }

  /**
   * The default value for tolerance.
   *
   * @return the default value
   * @see #m_Tolerance
   */
  protected double getDefaultTolerance() {
    return 0.001;
  }

  /**
   * Returns the convergence tolerance.
   *
   * @return the current value (value > 0.0)
   * @see #m_Tolerance
   */
  public double getTolerance() {
    return m_Tolerance;
  }

  /**
   * Sets the convergence tolerance.
   *
   * @param value the new value (value > 0.0)
   * @see #m_Tolerance
   */
  public void setTolerance(double value) {
    if (value > 0.0) {
      m_Tolerance = value;
    }
  }

  /**
   * Returns the help string for tolerance.
   *
   * @return the help string
   * @see #m_Tolerance
   */
  public String toleranceTipText() {
    return "The convergence tolerance; value > 0.0.";
  }
}
