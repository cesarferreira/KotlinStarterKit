package cesarferreira.movies.presentation.features.details

import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.toolbar.*
import cesarferreira.movies.R
import cesarferreira.movies.domain.MovieDetails
import cesarferreira.movies.presentation.features.common.Constants
import cesarferreira.movies.presentation.framework.base.BaseFragment
import javax.inject.Inject

class DetailsFragment : BaseFragment(), DetailsView {

    override fun layoutId(): Int = R.layout.fragment_details

    @Inject lateinit var picasso: Picasso
    @Inject lateinit var presenter: DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)

//        shimmer_view_container.startShimmerAnimation()
//        shimmer_view_container.



        presenter.fetchData(activity!!.intent.getStringExtra(Constants.ITEM_KEY))
    }

    override fun displayDetails(movie: MovieDetails) {

        movieCast.text = movie.cast
        movieDirector.text = movie.director
        movieSummary.text = movie.summary
        movieYear.text = movie.year

        activity!!.toolbar.title = movie.title

        picasso.load(movie.poster)
                .placeholder(R.color.black)
                .error(R.color.picassoError)
                .into(moviePoster)
    }

    override fun hideLoading() {
//        progressBar.visibility = View.GONE
    }

    override fun showError(throwable: Throwable) {
        TODO()
    }

    override fun showLoading() {
//        progressBar.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}

