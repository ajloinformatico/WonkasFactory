package es.infolojo.wonkasfactory.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.infolojo.wonkasfactory.data.vo.WonkaWorkerVO

class WonkasListAdapter(
    private val listener: (WonkasListAdapterAction) -> Unit,
): ListAdapter<WonkaWorkerVO, RecyclerView.ViewHolder>(WonkasListDiffUtil())

{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        //return

        return when (viewType.CoverViewType()) {
            CoverViewType.BIG_NEW -> {
                val binding =
                    RowBigNewCoverBinding.inflate(layoutInflater, parent, false)
                CoverHolder.FirstNewViewHolder(binding)
            }

            CoverViewType.NORMAL_NEW -> {
                val binding =
                    RowNormalNewCoverBinding.inflate(layoutInflater, parent, false)
                CoverHolder.NewsViewHolder(binding)
            }

            CoverViewType.OPINION_NEW -> {
                val binding =
                    RowNormalOpinionCoverBinding.inflate(layoutInflater, parent, false)
                CoverHolder.OpinionViewHolder(binding)
            }

            CoverViewType.SECTION_NEW -> {
                val binding =
                    RowCoverTltleSectionBinding.inflate(layoutInflater, parent, false)
                CoverHolder.SectionTitleViewHolder(binding)
            }

            CoverViewType.NEW_MATCH_HEADER -> {
                val binding =
                    RowTitleBinding.inflate(layoutInflater, parent, false)
                CoverHolder.MatchCoverHeaderViewHolder(binding)
            }

            CoverViewType.CAROUSSEL_VIDEO -> {
                val binding =
                    RowCoverNewsVideoCarouselListBinding.inflate(layoutInflater, parent, false)
                CoverHolder.VideoCarouselViewHolder(binding)
            }

            CoverViewType.MATCH_STARTED -> {
                val binding =
                    RowLiveScoreboardNotStartedBinding.inflate(layoutInflater, parent, false)
                CoverHolder.MatchCoverNotStartedViewHolder(binding)
            }

            CoverViewType.MATCH_FINISHED -> {
                val binding =
                    RowLiveScoreboardFinishedBinding.inflate(layoutInflater, parent, false)
                CoverHolder.MatchCoverFinishedViewHolder(binding)
            }

            CoverViewType.MATCH_ONLIVE -> {
                val binding =
                    RowLiveScoreboardNotStartedBinding.inflate(layoutInflater, parent, false)
                CoverHolder.MatchCoverOnliveViewHolder(binding)

            }

            CoverViewType.ROBA_AD -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_ads_robapaginas, parent, false)
                RobapaginasHolder(itemView)
            }

            CoverViewType.PHOTO_MATCH -> {
                val binding =
                    RowPhotoBigNewMatchBinding.inflate(layoutInflater, parent, false)
                CoverHolder.PhotoMatchViewHolder(binding)
            }

            CoverViewType.OPINION_BIG_NEW -> {
                val binding =
                    RowHeaderOpinionBinding.inflate(layoutInflater, parent, false)
                CoverHolder.OpinionMiniCoverHeaderHolder(binding)
            }

            CoverViewType.OPINION_MINI_COVER_BODY -> {
                val binding =
                    RowOpinionCoverBodyBinding.inflate(layoutInflater, parent, false)
                CoverHolder.OpinionMiniCoverBodyHolder(binding)
            }

            CoverViewType.AS_VIDEO -> {
                val binding =
                    RowPhotoVideoCoverBodyBinding.inflate(layoutInflater, parent, false)
                CoverHolder.AsVideoHolder(binding)
            }

            CoverViewType.GALLERY -> {
                val binding =
                    RowPhotoVideoCoverBodyBinding.inflate(layoutInflater, parent, false)
                CoverHolder.GalleryHolder(binding)
            }

            CoverViewType.CAROUSSEL_VIDEO_TEMP, CoverViewType.UNKNOWN,
            -> {
                val binding =
                    RowCoverEmptyLayoutBinding.inflate(layoutInflater, parent, false)
                CoverHolder.EmptyViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (viewHolder) {
            is CoverHolder.MatchCoverFinishedViewHolder -> {
                (getItem(position) as? CoverElementVO.MatchNewFinished?)?.let {
                    viewHolder.bind(
                        it,
                        listener,
                    )
                }
            }

            is CoverHolder.VideoCarouselViewHolder -> {
                (getItem(position) as? CoverElementVO.CarousselVideo?)?.let {
                    viewHolder.bind(
                        it,
                        listener,
                    )
                }
            }

            is CoverHolder.FirstNewViewHolder -> {
                (getItem(position) as? CoverElementVO.New.BigNew?)?.let {
                    viewHolder.bind(
                        it,
                        listener,
                    )
                }
            }

            is CoverHolder.MatchCoverNotStartedViewHolder -> {
                (getItem(position) as? CoverElementVO.MatchNewNotStarted?)?.let {
                    viewHolder.bind(
                        it,
                        listener,
                    )
                }
            }

            is CoverHolder.MatchCoverOnliveViewHolder -> {
                (getItem(position) as? CoverElementVO.MatchNewOnlive?)?.let {
                    viewHolder.bind(
                        it,
                        listener,
                    )
                }
            }

            is CoverHolder.NewsViewHolder -> {
                (getItem(position) as? CoverElementVO.New.NormalNew?)?.let {
                    viewHolder.bind(
                        it,
                        listener,
                    )
                }

            }

            is CoverHolder.OpinionViewHolder -> {
                (getItem(position) as? CoverElementVO.NewOpinion?)?.let {
                    viewHolder.bind(
                        it,
                        listener,
                    )
                }
            }

            is CoverHolder.SectionTitleViewHolder -> {
                (getItem(position) as? CoverElementVO.SectionNew?)?.let {
                    viewHolder.bind(
                        it,
                    )
                }
            }

            is CoverHolder.PhotoMatchViewHolder -> {
                (getItem(position) as? CoverElementVO.PhotoMatch?)?.let {
                    viewHolder.bind(
                        it,
                        listener,
                    )
                }
            }

            is RobapaginasHolder -> {
                val item = getItem(position) as? CoverElementVO.RobaAd?
                if (item != null) {
                    viewHolder.bindView(
                        item.contentUrl,
                        item.itemSection,
                        item.itemPosition,
                        isShowLine = false,
                        isComingFromDetail = false,
                        manager,
                    )
                }
            }

            is CoverHolder.MatchCoverHeaderViewHolder -> {
                (getItem(position) as? CoverElementVO.NewMatchHeader?)?.let {
                    viewHolder.bind(
                        it,
                        listener,
                    )
                }
            }

            is CoverHolder.OpinionMiniCoverHeaderHolder -> {
                (getItem(position) as? CoverElementVO.OpinionBigNew?)?.let {
                    viewHolder.bind(
                        it,
                        listener,
                    )
                }
            }

            is CoverHolder.OpinionMiniCoverBodyHolder -> {
                (getItem(position) as? CoverElementVO.OpinionMiniCoverBody?)?.let {
                    viewHolder.bind(
                        it,
                        listener,
                    )
                }
            }

            is CoverHolder.AsVideoHolder -> {
                (getItem(position) as? CoverElementVO.AsVideo)?.let {
                    viewHolder.bind(
                        it,
                        listener,
                    )
                }
            }

            is CoverHolder.GalleryHolder -> {
                (getItem(position) as? CoverElementVO.GalleryImage)?.let {
                    viewHolder.bind(
                        it,
                        listener,
                    )
                }
            }

            is CoverHolder.EmptyViewHolder -> {
                /*no-op*/
            }

        }
    }


    class WonkasListDiffUtil : DiffUtil.ItemCallback<WonkaWorkerVO>() {

    }

}