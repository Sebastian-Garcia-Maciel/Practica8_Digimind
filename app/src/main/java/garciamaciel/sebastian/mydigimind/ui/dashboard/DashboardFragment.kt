package garciamaciel.sebastian.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import garciamaciel.sebastian.mydigimind.R
import garciamaciel.sebastian.mydigimind.ui.Task
import garciamaciel.sebastian.mydigimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val btn_time: Button = root.findViewById(R.id.btn_time)

        btn_time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(
                root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true
            ).show()
        }

        var btn_save = root.findViewById(R.id.btn_save) as Button
        var et_titulo = root.findViewById(R.id.et_task) as EditText
        var checkMonday = root.findViewById(R.id.checkMonday) as CheckBox
        var checkTuesday = root.findViewById(R.id.checkTuesday) as CheckBox
        var checkWednesday = root.findViewById(R.id.checkWednesday) as CheckBox
        var checkThursday = root.findViewById(R.id.checkThursday) as CheckBox
        var checkFriday = root.findViewById(R.id.checkFriday) as CheckBox
        var checkSaturday = root.findViewById(R.id.checkSaturday) as CheckBox
        var checkSunday = root.findViewById(R.id.checkSunday) as CheckBox


        btn_save.setOnClickListener {
            var title = et_titulo.text.toString()
            var time = btn_time.text.toString()
            var days = ArrayList<String>()


            if (time.equals("Set time")) {
                Toast.makeText(root.context, "You must set the time", Toast.LENGTH_SHORT).show()
            } else {
                if (checkMonday.isChecked)
                    days.add("Monday")
                if (checkTuesday.isChecked)
                    days.add("Tuesday")
                if (checkWednesday.isChecked)
                    days.add("Wednesday")
                if (checkThursday.isChecked)
                    days.add("Thursday")
                if (checkFriday.isChecked)
                    days.add("Friday")
                if (checkSaturday.isChecked)
                    days.add("Saturday")
                if (checkSunday.isChecked)
                    days.add("Sunday")

                var task = Task(title, days, time)

                HomeFragment.tasks.add(task)
                Toast.makeText(root.context, "New task added", Toast.LENGTH_SHORT).show()
            }

        }

        return root
    }
}