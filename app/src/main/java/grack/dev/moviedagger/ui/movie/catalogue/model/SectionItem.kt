package grack.dev.moviedagger.ui.movie.catalogue.model

import com.intrusoft.sectionedrecyclerview.Section

class SectionItem(
  var title: String,
  var result: ArrayList<Child>
) : Section<Child> {
  override fun getChildItems(): ArrayList<Child> {
    return result
  }
}
