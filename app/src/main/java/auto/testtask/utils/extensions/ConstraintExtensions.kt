package auto.testtask.utils.extensions

import androidx.constraintlayout.widget.ConstraintSet


fun ConstraintSet.connectToParentsTop(viewIdToConnect: Int) {
    connectTopToTop(viewIdToConnect, ConstraintSet.PARENT_ID)
}

fun ConstraintSet.connectToParentsBottom(viewIdToConnect: Int) {
    connectBottomToBottom(viewIdToConnect, ConstraintSet.PARENT_ID)
}

fun ConstraintSet.connectToParentsStart(viewIdToConnect: Int) {
    connectStartToStart(viewIdToConnect, ConstraintSet.PARENT_ID)
}

fun ConstraintSet.connectToParentsEnd(viewIdToConnect: Int) {
    connectEndToEnd(viewIdToConnect, ConstraintSet.PARENT_ID)
}

fun ConstraintSet.connectToParentStartEnd(viewIdToConnect: Int) {
    connectToParentsStart(viewIdToConnect)
    connectToParentsEnd(viewIdToConnect)
}

fun ConstraintSet.connectToParentTopBottom(viewIdToConnect: Int) {
    connectToParentsTop(viewIdToConnect)
    connectToParentsBottom(viewIdToConnect)
}

fun ConstraintSet.connectToParentSides(viewIdToConnect: Int) {
    connectToParentTopBottom(viewIdToConnect)
    connectToParentStartEnd(viewIdToConnect)
}

fun ConstraintSet.connectTopToTop(startId: Int, endId: Int) {
    connect(startId, ConstraintSet.TOP, endId, ConstraintSet.TOP)
}

fun ConstraintSet.connectTopToBottom(startId: Int, endId: Int) {
    connect(startId, ConstraintSet.TOP, endId, ConstraintSet.BOTTOM)
}

fun ConstraintSet.connectStartToStart(startId: Int, endId: Int) {
    connect(startId, ConstraintSet.START, endId, ConstraintSet.START)
}

fun ConstraintSet.connectStartToEnd(startId: Int, endId: Int) {
    connect(startId, ConstraintSet.START, endId, ConstraintSet.END)
}

fun ConstraintSet.connectBottomToBottom(startId: Int, endId: Int) {
    connect(startId, ConstraintSet.BOTTOM, endId, ConstraintSet.BOTTOM)
}

fun ConstraintSet.connectBottomToTop(startId: Int, endId: Int) {
    connect(startId, ConstraintSet.BOTTOM, endId, ConstraintSet.TOP)
}

fun ConstraintSet.connectEndToEnd(startId: Int, endId: Int) {
    connect(startId, ConstraintSet.END, endId, ConstraintSet.END)
}

fun ConstraintSet.connectEndToStart(startId: Int, endId: Int) {
    connect(startId, ConstraintSet.END, endId, ConstraintSet.START)
}