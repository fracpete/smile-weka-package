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
 * SmileRidgeRegressionBase.java
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

/**
 * Superclass for SmileRidgeRegression containing the option handling.
 *
 * @author FracPete
 */
public abstract class SmileRidgeRegressionBase
  extends AbstractSmileRegressor {

  /** the flag for {@link #m_Lambda}. */
  public final static String LAMBDA = "lambda";

  /** the shrinkage/regularization factor */
  protected double m_Lambda = getDefaultLambda();

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
    WekaOptionUtils.addOption(result, lambdaTipText(), "" + getDefaultLambda(), LAMBDA);
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
    setLambda(WekaOptionUtils.parse(options, LAMBDA, getDefaultLambda()));
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
    WekaOptionUtils.add(result, LAMBDA, getLambda());
    WekaOptionUtils.add(result, super.getOptions());
    return WekaOptionUtils.toArray(result);
  }

  /**
   * The default value for lambda.
   *
   * @return the default value
   * @see #m_Lambda
   */
  protected double getDefaultLambda() {
    return 1.0e-8;
  }

  /**
   * Returns the shrinkage/regularization factor
   *
   * @return the current value (value >= 0.0)
   * @see #m_Lambda
   */
  public double getLambda() {
    return m_Lambda;
  }

  /**
   * Sets the shrinkage/regularization factor
   *
   * @param value the new value (value >= 0.0)
   * @see #m_Lambda
   */
  public void setLambda(double value) {
    if (value >= 0.0) {
      m_Lambda = value;
    }
  }

  /**
   * Returns the help string for lambda.
   *
   * @return the help string
   * @see #m_Lambda
   */
  public String lambdaTipText() {
    return "The shrinkage/regularization facto; value >= 0.0.";
  }
}
