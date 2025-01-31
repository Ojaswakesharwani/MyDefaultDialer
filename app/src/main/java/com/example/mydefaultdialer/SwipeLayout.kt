import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

class SwipeLayout(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private var listener: (() -> Unit)? = null

    fun setOnSwipeListener(listener: () -> Unit) {
        this.listener = listener
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) {
            listener?.invoke()
        }
        return true
    }
}
