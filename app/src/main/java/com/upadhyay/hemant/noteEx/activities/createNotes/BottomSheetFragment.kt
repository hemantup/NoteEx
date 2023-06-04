package com.upadhyay.hemant.noteEx.activities.createNotes

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.upadhyay.hemant.noteEx.R
import com.upadhyay.hemant.noteEx.Util.EMPTY_STRING
import com.upadhyay.hemant.noteEx.Util.makeGone
import com.upadhyay.hemant.noteEx.Util.makeVisible
import com.upadhyay.hemant.noteEx.databinding.FragmentBottomSheetBinding

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!
    private var selectedColor = "#3e434e"

    companion object {

        var noteId:Int? = null

        const val DRAGGING = "DRAGGING"
        const val SETTLING = "SETTLING"
        const val EXPANDED = "EXPANDED"
        const val COLLAPSED = "COLLAPSED"
        const val HIDDEN = "HIDDEN"

        fun newInstance(id: Int?): BottomSheetFragment {
            val args = Bundle()
            val fragment = BottomSheetFragment()
            fragment.arguments = args
            noteId = id
            return fragment
        }
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)

        val view = LayoutInflater.from(context).inflate(R.layout.fragment_bottom_sheet, null)
        dialog.setContentView(view)

        val param = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams

        val behavior = param.behavior

        if (behavior is BottomSheetBehavior<*>) {

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    var state = EMPTY_STRING
                    while (newState == newState) {
                        BottomSheetBehavior.STATE_DRAGGING.apply {
                            state = DRAGGING
                        }
                        BottomSheetBehavior.STATE_SETTLING.apply {
                            state = SETTLING
                        }
                        BottomSheetBehavior.STATE_EXPANDED.apply {
                            state = EXPANDED
                        }
                        BottomSheetBehavior.STATE_COLLAPSED.apply {
                            state = COLLAPSED
                        }
                        BottomSheetBehavior.STATE_HIDDEN.apply {
                            state = HIDDEN
                            dismiss()
                            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                        }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (noteId != null) {
            binding.layoutDeleteNote.makeVisible()
        } else {
            binding.layoutDeleteNote.makeGone()
        }
        setListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListener() {

        binding.apply {

            fNoteBlue.setOnClickListener {

                imgNoteBlue.setImageResource(R.drawable.ic_done)
                imgNoteCyan.setImageResource(0)
                imgNoteGreen.setImageResource(0)
                imgNoteOrange.setImageResource(0)
                imgNotePurple.setImageResource(0)
                imgNoteRed.setImageResource(0)
                imgNoteYellow.setImageResource(0)
                imgNoteBrown.setImageResource(0)
                imgNoteIndigo.setImageResource(0)
                selectedColor = "#2196f3"

                val intent = Intent(getString(R.string.bottom_sheet_action))
                intent.putExtra(getString(R.string.action), getString(R.string.blue))
                intent.putExtra(getString(R.string.selected_color), selectedColor)
                LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
            }

            fNoteCyan.setOnClickListener {

                imgNoteBlue.setImageResource(0)
                imgNoteCyan.setImageResource(R.drawable.ic_done)
                imgNoteGreen.setImageResource(0)
                imgNoteOrange.setImageResource(0)
                imgNotePurple.setImageResource(0)
                imgNoteRed.setImageResource(0)
                imgNoteYellow.setImageResource(0)
                imgNoteBrown.setImageResource(0)
                imgNoteIndigo.setImageResource(0)
                selectedColor = "#00e5ff"

                val intent = Intent(getString(R.string.bottom_sheet_action))
                intent.putExtra(getString(R.string.action), getString(R.string.cyan))
                intent.putExtra(getString(R.string.selected_color), selectedColor)
                LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
            }

            fNoteGreen.setOnClickListener {

                imgNoteBlue.setImageResource(0)
                imgNoteCyan.setImageResource(0)
                imgNoteGreen.setImageResource(R.drawable.ic_done)
                imgNoteOrange.setImageResource(0)
                imgNotePurple.setImageResource(0)
                imgNoteRed.setImageResource(0)
                imgNoteYellow.setImageResource(0)
                imgNoteBrown.setImageResource(0)
                imgNoteIndigo.setImageResource(0)
                selectedColor = "#00c853"

                val intent = Intent(getString(R.string.bottom_sheet_action))
                intent.putExtra(getString(R.string.action), getString(R.string.green))
                intent.putExtra(getString(R.string.selected_color), selectedColor)
                LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
            }

            fNoteOrange.setOnClickListener {

                imgNoteBlue.setImageResource(0)
                imgNoteCyan.setImageResource(0)
                imgNoteGreen.setImageResource(0)
                imgNoteOrange.setImageResource(R.drawable.ic_done)
                imgNotePurple.setImageResource(0)
                imgNoteRed.setImageResource(0)
                imgNoteYellow.setImageResource(0)
                imgNoteBrown.setImageResource(0)
                imgNoteIndigo.setImageResource(0)
                selectedColor = "#ff6d00"

                val intent = Intent(getString(R.string.bottom_sheet_action))
                intent.putExtra(getString(R.string.action), getString(R.string.orange))
                intent.putExtra(getString(R.string.selected_color), selectedColor)
                LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
            }

            fNotePurple.setOnClickListener {

                imgNoteBlue.setImageResource(0)
                imgNoteCyan.setImageResource(0)
                imgNoteGreen.setImageResource(0)
                imgNoteOrange.setImageResource(0)
                imgNotePurple.setImageResource(R.drawable.ic_done)
                imgNoteRed.setImageResource(0)
                imgNoteYellow.setImageResource(0)
                imgNoteBrown.setImageResource(0)
                imgNoteIndigo.setImageResource(0)
                selectedColor = "#aa00ff"

                val intent = Intent(getString(R.string.bottom_sheet_action))
                intent.putExtra(getString(R.string.action), getString(R.string.purple))
                intent.putExtra(getString(R.string.selected_color), selectedColor)
                LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
            }

            fNoteRed.setOnClickListener {

                imgNoteBlue.setImageResource(0)
                imgNoteCyan.setImageResource(0)
                imgNoteGreen.setImageResource(0)
                imgNoteOrange.setImageResource(0)
                imgNotePurple.setImageResource(0)
                imgNoteRed.setImageResource(R.drawable.ic_done)
                imgNoteYellow.setImageResource(0)
                imgNoteBrown.setImageResource(0)
                imgNoteIndigo.setImageResource(0)
                selectedColor = "#d50000"

                val intent = Intent(getString(R.string.bottom_sheet_action))
                intent.putExtra(getString(R.string.action), getString(R.string.red))
                intent.putExtra(getString(R.string.selected_color), selectedColor)
                LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
            }

            fNoteYellow.setOnClickListener {

                imgNoteBlue.setImageResource(0)
                imgNoteCyan.setImageResource(0)
                imgNoteGreen.setImageResource(0)
                imgNoteOrange.setImageResource(0)
                imgNotePurple.setImageResource(0)
                imgNoteRed.setImageResource(0)
                imgNoteYellow.setImageResource(R.drawable.ic_done)
                imgNoteBrown.setImageResource(0)
                imgNoteIndigo.setImageResource(0)
                selectedColor = "#ffeb3b"

                val intent = Intent(getString(R.string.bottom_sheet_action))
                intent.putExtra(getString(R.string.action), getString(R.string.yellow))
                intent.putExtra(getString(R.string.selected_color), selectedColor)
                LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
            }

            fNoteBrown.setOnClickListener {

                imgNoteBlue.setImageResource(0)
                imgNoteCyan.setImageResource(0)
                imgNoteGreen.setImageResource(0)
                imgNoteOrange.setImageResource(0)
                imgNotePurple.setImageResource(0)
                imgNoteRed.setImageResource(0)
                imgNoteYellow.setImageResource(0)
                imgNoteBrown.setImageResource(R.drawable.ic_done)
                imgNoteIndigo.setImageResource(0)
                selectedColor = "#3e2723"

                val intent = Intent(getString(R.string.bottom_sheet_action))
                intent.putExtra(getString(R.string.action), getString(R.string.brown))
                intent.putExtra(getString(R.string.selected_color), selectedColor)
                LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
            }

            fNoteIndigo.setOnClickListener {

                imgNoteBlue.setImageResource(0)
                imgNoteCyan.setImageResource(0)
                imgNoteGreen.setImageResource(0)
                imgNoteOrange.setImageResource(0)
                imgNotePurple.setImageResource(0)
                imgNoteRed.setImageResource(0)
                imgNoteYellow.setImageResource(0)
                imgNoteBrown.setImageResource(0)
                imgNoteIndigo.setImageResource(R.drawable.ic_done)
                selectedColor = "#1a237e"

                val intent = Intent(getString(R.string.bottom_sheet_action))
                intent.putExtra(getString(R.string.action), getString(R.string.indigo))
                intent.putExtra(getString(R.string.selected_color), selectedColor)
                LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
            }
            // FINISH COLORS

            // ADD IMAGE
            layoutImage.setOnClickListener {

                val intent = Intent(getString(R.string.bottom_sheet_action))
                intent.putExtra(getString(R.string.action), getString(R.string.image))
                LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
                dismiss()
            }

            // ADD URL
            layoutWebUrl.setOnClickListener {

                val intent = Intent(getString(R.string.bottom_sheet_action))
                intent.putExtra(getString(R.string.action), getString(R.string.webUrl))
                LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
                dismiss()
            }

            // Delete Notes
            layoutDeleteNote.setOnClickListener {

                val intent = Intent(getString(R.string.bottom_sheet_action))
                intent.putExtra(getString(R.string.action), getString(R.string.deleteNote))
                LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
                dismiss()
            }
        }
    }
}