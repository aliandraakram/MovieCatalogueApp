package com.bangkit.moviesandtvshowsapp.utils

import com.bangkit.moviesandtvshowsapp.dataclass.entity.TvShowsEntity

object DataTvShows {

    fun getTvShows(): List<TvShowsEntity> {

        val tvShows = mutableListOf<TvShowsEntity>()

        tvShows.add(
            TvShowsEntity(
                88396,
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "The Falcon and the Winter Soldier",
                "2021-03-19",
                1,
                "Action & Adventure, Sci-Fi & Fantasy, Drama, War & Politics ",
                7.9,
                "50",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                false,
            )

        )

        tvShows.add(
            TvShowsEntity(
                93484,
                "/9yxep7oJdkj3Pla9TD9gKflRApY.jpg",
                "Jupiter's Legacy",
                "2021-05-07",
                1,
                "Sci-Fi & Fantasy, Action & Adventure, Drama",
                7.5,
                "5",
                "When the world's first generation of superheroes received their powers in the 1930s become the revered elder guard in the present, their superpowered children struggle to live up to the legendary feats of their parents.",
                false
            )
        )

        tvShows.add(
            TvShowsEntity(
                95557,
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "Invincible",
                "2021-03-26",
                1,
                "Animation, Action & Adventure, Drama, Sci-Fi & Fantasy",
                8.9,
                "45",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                false
            )
        )

        tvShows.add(
            TvShowsEntity(
                71712,
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "The Good Doctor",
                "2017-09-25",
                4,
                "Drama",
                8.6,
                "43",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                false
            )
        )

        tvShows.add(
            TvShowsEntity(
                60735,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "The Flash",
                "2014-10-07",
                7,
                "Drama, Sci-Fi & Fantasy",
                7.7,
                "44",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                false
            )
        )

        tvShows.add(
            TvShowsEntity(
                105971,
                "/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg",
                "The Bad Batch",
                "2021-05-04",
                1,
                "Sci-Fi & Fantasy, Action & Adventure, Animation ",
                9.0,
                "75",
                "Follow the elite and experimental Clones of the Bad Batch as they find their way in a rapidly changing galaxy in the aftermath of the Clone Wars.",
                false
            )
        )

        tvShows.add(
            TvShowsEntity(
                1416,
                "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "Grey's Anatomy",
                "2005-03-27",
                17,
                "Drama",
                8.2,
                "43",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                false
            )
        )

        tvShows.add(
            TvShowsEntity(
                63174,
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "Lucifer",
                "2016-01-25",
                6,
                "Crime, Sci-Fi & Fantasy,",
                8.5,
                "45",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                false
            )
        )

        tvShows.add(
            TvShowsEntity(
                69050,
                "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                "Grey\'s anatomy",
                "2017-01-26",
                5,
                "Mystery, Drama, Crime",
                8.6,
                "45",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                false
            )
        )

        tvShows.add(
            TvShowsEntity(
                97180,
                "/mYsWyfiIMxx4HDm0Wck7oJ9ckez.jpg",
                "Selena: The Series",
                "2020-12-04",
                1,
                "Drama",
                7.5,
                "40",
                "As Mexican-American Tejano singer Selena comes of age and realizes her dreams, she and her family make tough choices to hold on to love and music.",
                false
            )
        )

        return tvShows
    }
}