/*
 * Copyright (c) 2011-2014 Jarek Sacha. All Rights Reserved.
 *
 * Author's e-mail: jpsacha at gmail.com
 */

package opencv2_cookbook.chapter05

import java.io.File

import opencv2_cookbook.OpenCVUtils._
import org.bytedeco.javacpp.opencv_core._
import org.bytedeco.javacpp.opencv_imgcodecs._
import org.bytedeco.javacpp.opencv_imgproc._


/**
 * Example from section "Extracting foreground objects with the GrabCut algorithm".
 */
object Ex5GrabCut extends App {

  // Open image
  //  val image = loadCvMatAndShowOrExit(new File("data/group.jpg"), IMREAD_COLOR)
  val image = loadAndShowOrExit(new File("data/group.jpg"), IMREAD_COLOR)

  // Define bounding rectangle, pixels outside this rectangle will be labeled as background.
  val rectangle = new Rect(10, 100, 380, 180)

  val result = new Mat()
  val iterCount = 5
  val mode = GC_INIT_WITH_RECT

  // Need to allocate arrays for temporary data
  val bgdModel = new Mat()
  val fgdModel = new Mat()

  // GrabCut segmentation
  grabCut(image, result, rectangle, bgdModel, fgdModel, iterCount, mode)

  // Prepare image for display: extract foreground
  threshold(result, result, GC_PR_FGD - 0.5, GC_PR_FGD + 0.5, THRESH_BINARY)
  show(toMat8U(result), "Result foreground mask")
}