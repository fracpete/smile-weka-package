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
 * SmilePearsonKernel.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package smile.math.kernel;

/**
 * SMILE PearsonKernel.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class SmilePearsonKernel
  extends SmilePearsonKernelBase {

  private static final long serialVersionUID = 2662812690103741242L;

  /**
   * Returns a description of the classifier.
   *
   * @return the description
   */
  public String globalInfo() {
    return "The Pearson Mercer Kernel.";
  }

  /**
   * Returns a new instance of the kernel.
   *
   * @return		the kernel
   */
  @Override
  public PearsonKernel getKernel() {
    return new PearsonKernel(m_Omega, m_Sigma);
  }
}
