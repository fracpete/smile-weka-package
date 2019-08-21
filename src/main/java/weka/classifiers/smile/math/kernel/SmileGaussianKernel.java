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
 * SmileGaussianKernel.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.classifiers.smile.math.kernel;

import smile.math.kernel.GaussianKernel;
import weka.core.WekaOptionUtils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * SMILE GaussianKernel.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmileGaussianKernel
  extends AbstractSmileKernel<GaussianKernel> {

  private static final long serialVersionUID = 2662812690103741242L;

  public static final String SIGMA = "sigma";

  /** the sigma parameter. */
  protected double m_Sigma = getDefaultSigma();

  /**
   * Returns a description of the classifier.
   *
   * @return the description
   */
  public String globalInfo() {
    return "The Gaussian Mercer Kernel.";
  }

  /**
   * Returns an enumeration describing the available options.
   *
   * @return an enumeration of all the available options.
   */
  @Override
  public Enumeration listOptions() {
    Vector result = new Vector();
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
    WekaOptionUtils.add(result, SIGMA, getSigma());
    WekaOptionUtils.add(result, super.getOptions());
    return WekaOptionUtils.toArray(result);
  }

  /**
   * The default sigma.
   *
   * @return		the default
   */
  protected double getDefaultSigma() {
    return 0.01;
  }

  /**
   * Sets the sigma.
   *
   * @param value	the sigma (>= 0)
   */
  public void setSigma(double value) {
    if (value >= 0.0)
      m_Sigma = value;
  }

  /**
   * Returns the sigma.
   *
   * @return		the sigma (>= 0)
   */
  public double getSigma() {
    return m_Sigma;
  }

  /**
   * Returns the help string.
   *
   * @return		the help string
   */
  public String sigmaTipText() {
    return "The sigma (>= 0).";
  }

  /**
   * Returns a new instance of the kernel.
   *
   * @return		the kernel
   */
  @Override
  public GaussianKernel getKernel() {
    return new GaussianKernel(m_Sigma);
  }
}
