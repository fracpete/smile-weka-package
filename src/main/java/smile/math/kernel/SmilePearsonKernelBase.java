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
 * SmilePearsonKernelBase.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package smile.math.kernel;

import weka.core.Utils;
import weka.core.WekaOptionUtils;
import smile.math.kernel.AbstractSmileKernel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * Superclass for SmilePearsonKernel containing the option handling.
 *
 * @author FracPete
 */
public abstract class SmilePearsonKernelBase
  extends AbstractSmileKernel<smile.math.kernel.PearsonKernel> {

  /** the flag for {@link #m_Omega}. */
  public final static String OMEGA = "omega";

  /** the flag for {@link #m_Sigma}. */
  public final static String SIGMA = "sigma";

  /** the sigma value. */
  protected double m_Omega = getDefaultOmega();

  /** the sigma value. */
  protected double m_Sigma = getDefaultSigma();

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
    WekaOptionUtils.addOption(result, omegaTipText(), "" + getDefaultOmega(), OMEGA);
    WekaOptionUtils.addOption(result, sigmaTipText(), "" + getDefaultSigma(), SIGMA);
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
    setOmega(WekaOptionUtils.parse(options, OMEGA, getDefaultOmega()));
    setSigma(WekaOptionUtils.parse(options, SIGMA, getDefaultSigma()));
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
    WekaOptionUtils.add(result, OMEGA, getOmega());
    WekaOptionUtils.add(result, SIGMA, getSigma());
    WekaOptionUtils.add(result, super.getOptions());
    return WekaOptionUtils.toArray(result);
  }

  /**
   * The default value for omega.
   *
   * @return the default value
   * @see #m_Omega
   */
  protected double getDefaultOmega() {
    return 1.0;
  }

  /**
   * Returns the sigma value.
   *
   * @return the current value
   * @see #m_Omega
   */
  public double getOmega() {
    return m_Omega;
  }

  /**
   * Sets the sigma value.
   *
   * @param value the new value
   * @see #m_Omega
   */
  public void setOmega(double value) {
    m_Omega = value;
  }

  /**
   * Returns the help string for omega.
   *
   * @return the help string
   * @see #m_Omega
   */
  public String omegaTipText() {
    return "The sigma value.";
  }

  /**
   * The default value for sigma.
   *
   * @return the default value
   * @see #m_Sigma
   */
  protected double getDefaultSigma() {
    return 1.0;
  }

  /**
   * Returns the sigma value.
   *
   * @return the current value
   * @see #m_Sigma
   */
  public double getSigma() {
    return m_Sigma;
  }

  /**
   * Sets the sigma value.
   *
   * @param value the new value
   * @see #m_Sigma
   */
  public void setSigma(double value) {
    m_Sigma = value;
  }

  /**
   * Returns the help string for sigma.
   *
   * @return the help string
   * @see #m_Sigma
   */
  public String sigmaTipText() {
    return "The sigma value.";
  }
}
