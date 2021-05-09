package vitriol.api_aws.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vitriol.api_aws.web.dto.PostsListResponseDto;
import vitriol.api_aws.web.dto.PostsResponseDto;
import vitriol.api_aws.web.dto.PostsSaveRequestDto;
import vitriol.api_aws.web.dto.PostsUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글은 존재하지 않습니다"));
        return new PostsResponseDto(posts);
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
        postsRepository.delete(posts);
    }
}
