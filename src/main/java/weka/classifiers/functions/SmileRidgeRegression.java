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
 * SmileRidgeRegression.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package weka.classifiers.functions;

import smile.data.AttributeDataset;
import smile.regression.Regression;
import smile.regression.RidgeRegression;
import weka.classifiers.AbstractSmileRegressor;

/**
 * SMILE RidgeRegression.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmileRidgeRegression
  extends AbstractSmileRegressor {

  private static final long serialVersionUID = -8861088582494627633L;

  /**
   * Returns a description of the classifier.
   *
   * @return the description
   */
  public String globalInfo() {
    return "Ridge Regression. Coefficient estimates for multiple linear regression "
      + "models rely on the independence of the model terms. When terms are "
      + "correlated and the columns of the design matrix X have an approximate "
      + "linear dependence, the matrix X'X becomes close to singular. As a result, "
      + "the least-squares estimate becomes highly sensitive to random errors in "
      + "the observed response Y, producing a large variance. ";
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
    return new RidgeRegression(data.x(), data.y(), 0.0001);
  }

  /**
   * Executes the classifier from the commandline.
   *
   * @param args	the options
   */
  public static void main(String[] args) {
    runClassifier(new SmileRidgeRegression(), args);
  }
}
